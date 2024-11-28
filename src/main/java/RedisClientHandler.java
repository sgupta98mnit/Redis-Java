import command.CommandRegistry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    public RedisClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                var out = clientSocket.getOutputStream();
        ) {
            // Loop to handle multiple commands from one client.
            while(!clientSocket.isClosed()) {
                String[] args;
                try {
                    args = RespParser.parse(reader);
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                    break;
                }
                String commandResponse = commandRegistry.getCommand(args[0]).execute(args);
                out.write(commandResponse.getBytes());
                out.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

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
