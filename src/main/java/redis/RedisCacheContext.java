package redis;

import lombok.Builder;
import lombok.Data;

public class RedisCacheContext {
    private String value;
    private long expiry;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getExpiry() {
        return expiry;
    }

    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }

    @Override
    public String toString() {
        return "RedisCacheContext{" +
                "value='" + value + '\'' +
                ", expiry=" + expiry +
                '}';
    }
}
