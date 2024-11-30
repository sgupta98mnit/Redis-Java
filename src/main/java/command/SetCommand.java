package command;

import org.apache.commons.lang3.StringUtils;
import redis.RedisCacheContext;
import redis.RedisClientContext;

public class SetCommand implements Command {

    @Override
    public String execute(RedisClientContext context) {
        RedisCacheContext redisCacheContext = new RedisCacheContext();
        redisCacheContext.setValue(StringUtils.trim(context.getValue()));
        redisCacheContext.setExpiry(System.currentTimeMillis() + context.getExpiry());
        context.getCache().put(StringUtils.trim(context.getKey()), redisCacheContext);
        System.out.println("Set command executed: " + context.getKey() + " "
                + context.getValue() + " Map size: " + context.getCache().size());
        return "+OK\r\n";
    }
}
