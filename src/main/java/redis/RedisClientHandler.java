package redis;

import command.CommandRegistry;
import lombok.Getter;
import lombok.Setter;
import parser.RespParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

@Getter
@Setter
public class RedisClientHandler implements Runnable {

    private final Socket clientSocket;
    private final CommandRegistry commandRegistry = new CommandRegistry();
    private final int THREAD_SLEEP_TIME = 100;

    public RedisClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                var out = clientSocket.getOutputStream();
        ) {
//            Loop to handle multiple commands from one client.
//            This loop will continue to run even when there is no input stream,
//            so handling thread sleep condition in loop.
            RedisClientContext context = new RedisClientContext();
            while(!clientSocket.isClosed()) {
                //If input stream empty, send thread to sleep
                if(!reader.ready()) {
                    Thread.sleep(THREAD_SLEEP_TIME);
                }
                try {
                    RespParser.parse(reader, context);
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                    break;
                }
                System.out.println("Command: " + context.getCommand());
                String commandResponse = commandRegistry.getCommand(context.getCommand()).execute(context);
                System.out.println("Command response: " + commandResponse);
                out.write(commandResponse.getBytes());
                out.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        } catch(InterruptedException e) {
            System.out.println("Interrupted exception: " + e.getMessage());
        } finally {
            try {
                if(clientSocket != null) {
                    clientSocket.close();
                    System.out.println("Client disconnected: " + clientSocket.getRemoteSocketAddress());
                }
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
                e.printStackTrace();
            }

        }

    }
}
