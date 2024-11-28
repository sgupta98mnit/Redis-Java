package command;

public class EchoCommand implements Command {
    @Override
    public String execute(String[] args) {
        return "$" + args[1].length() + "\r\n" + args[1] + "\r\n";
    }
}
