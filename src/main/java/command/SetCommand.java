package command;

import org.apache.commons.lang3.StringUtils;
import redis.RedisContext;

import static utility.RedisUtility.transform;

public class SetCommand implements Command {

    @Override
    public String execute(RedisContext context) {
        context.getCache().put(transform(StringUtils.trim(context.getArgs()[1])), StringUtils.trim(context.getArgs()[2]));
        System.out.println("Set command executed: " + context.getArgs()[1] + " "
                + context.getArgs()[2] + " Map size: " + context.getCache().size());
        return "+OK\r\n";
    }
}
