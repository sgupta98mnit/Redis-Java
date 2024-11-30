package command;

import redis.RedisClientContext;

@FunctionalInterface
public interface Command {
    String execute(RedisClientContext context);
}
