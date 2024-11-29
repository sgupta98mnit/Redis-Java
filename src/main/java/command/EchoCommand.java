package command;

import redis.RedisContext;

import static utility.RedisUtility.transform;

public class EchoCommand implements Command {
    @Override
    public String execute(RedisContext context) {
        System.out.println("Echo command executed: " + context.getArgs()[1]);
        return transform(context.getArgs()[1]);
    }
}
