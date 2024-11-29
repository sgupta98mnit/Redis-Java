package command;

import redis.RedisContext;

public class SetCommand implements Command {

    @Override
    public String execute(RedisContext context) {
        context.getCache().put(context.getArgs()[1], context.getArgs()[2]);
        return "+OK\r\n";
    }
}
