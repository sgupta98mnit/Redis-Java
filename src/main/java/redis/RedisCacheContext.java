package redis;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RedisCacheContext {
    private String value;
    private long expiry;
}
