package command;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandRegistry() {
        commands.put("PING", args -> "+PONG\r\n");
        commands.put("ECHO", new EchoCommand());
        commands.put("SET", new SetCommand());
        commands.put("GET", new GetCommand());
    }

    public Command getCommand(String command) {
        return commands.getOrDefault(StringUtils.upperCase(command), args -> "Unknown command\r\n");
    }
}
