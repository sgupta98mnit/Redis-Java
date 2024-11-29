package command;

import redis.RedisContext;

public class GetCommand implements Command {

    @Override
    public String execute(RedisContext context) {
        return context.getCache().getOrDefault(context.getArgs()[1], "$-1\r\n");
    }
}
