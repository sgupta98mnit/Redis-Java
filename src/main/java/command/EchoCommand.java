package command;

import redis.RedisClientContext;

import static utility.RedisUtility.transform;

public class EchoCommand implements Command {
    @Override
    public String execute(RedisClientContext context) {
        System.out.println("Echo command executed: " + context.getKey());
        return transform(context.getKey());
    }
}
