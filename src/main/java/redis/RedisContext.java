package redis;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class RedisContext {
    private String[] args;
    private Map<String, String> cache = new HashMap<>();
}
