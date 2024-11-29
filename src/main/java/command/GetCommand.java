package command;

import redis.RedisContext;

import static utility.RedisUtility.transform;

public class GetCommand implements Command {

    @Override
    public String execute(RedisContext context) {
        return context.getCache().getOrDefault(transform(context.getArgs()[1]), "$-1\r\n");
    }

}
