package command;

import org.apache.commons.lang3.StringUtils;
import redis.RedisCacheContext;
import redis.RedisClientContext;

import static utility.RedisUtility.transform;

public class GetCommand implements Command {

    @Override
    public String execute(RedisClientContext context) {

        System.out.println("Get command executed: " + context.getKey() + " Map size: " + context.getCache().size());
//        context.getCache().forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
        String RESPONSE_NOT_FOUND = "$-1\r\n";
        RedisCacheContext emptyCacheContext = new RedisCacheContext();
        emptyCacheContext.setValue(RESPONSE_NOT_FOUND);
        RedisCacheContext cacheContext = context.getCache().getOrDefault(StringUtils.trim(context.getKey()),
                        emptyCacheContext);
        System.out.println("Cache Context: " + cacheContext);
        if(cacheContext.getExpiry() < context.getCurrentRequestTime()) {
            System.out.println("Cache Expired: " + cacheContext.getExpiry() + " Current time: " + System.currentTimeMillis());
            return RESPONSE_NOT_FOUND;
        }
        if(!StringUtils.equals(cacheContext.getValue(), RESPONSE_NOT_FOUND))
            cacheContext.setValue(transform(cacheContext.getValue()));
        return cacheContext.getValue();
    }

}
