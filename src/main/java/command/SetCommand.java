package command;

import org.apache.commons.lang3.StringUtils;
import redis.RedisCacheContext;
import redis.RedisClientContext;

public class SetCommand implements Command {

    @Override
    public String execute(RedisClientContext context) {
        RedisCacheContext redisCacheContext = new RedisCacheContext();
        redisCacheContext.setValue(StringUtils.trim(context.getValue()));
        System.out.println("Current time: " + System.currentTimeMillis() + " Expiry: " + context.getExpiry());
        redisCacheContext.setExpiry(context.getCurrentRequestTime() + context.getExpiry());
        context.getCache().put(StringUtils.trim(context.getKey()), redisCacheContext);
        System.out.println("clientContext: " + context);
        System.out.println("cacheContext: " + redisCacheContext);
        return "+OK\r\n";
    }
}
