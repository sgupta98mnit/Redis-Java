package redis;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class RedisContext {
    private String[] args;
    private Map<String, String> cache = new HashMap<>();
}
