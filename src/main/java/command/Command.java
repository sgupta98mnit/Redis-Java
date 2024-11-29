package command;

import redis.RedisContext;

@FunctionalInterface
public interface Command {
    String execute(RedisContext context);
}
