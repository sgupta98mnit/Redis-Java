package command;

public class EchoCommand implements Command {
    @Override
    public String execute(String[] args) {
        System.out.println("Echo command executed: " + args[1]);
        return "$" + args[1].length() + "\r\n" + args[1] + "\r\n";
    }
}
