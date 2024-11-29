package command;

import redis.RedisContext;

public class EchoCommand implements Command {
    @Override
    public String execute(RedisContext context) {
        System.out.println("Echo command executed: " + context.getArgs()[1]);
        return "$" + context.getArgs()[1].length() + "\r\n" + context.getArgs()[1] + "\r\n";
    }
}
