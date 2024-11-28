package command;

@FunctionalInterface
public interface Command {
    String execute(String[] args);
}
