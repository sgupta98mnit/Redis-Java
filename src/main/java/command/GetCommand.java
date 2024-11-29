package command;

import org.apache.commons.lang3.StringUtils;
import redis.RedisContext;

import static utility.RedisUtility.transform;

public class GetCommand implements Command {

    @Override
    public String execute(RedisContext context) {
        System.out.println("Get command executed: " + context.getArgs()[1] + " Map size: " + context.getCache().size());
        context.getCache().forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
        return context.getCache().getOrDefault(transform(StringUtils.trim(context.getArgs()[1])), "$-1\r\n");
    }

}
