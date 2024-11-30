package redis;

import java.util.HashMap;
import java.util.Map;


public class RedisClientContext {

    private Map<String, RedisCacheContext> cache = new HashMap<>();
    private String command;
    private String key;
    private String value;
    private long expiry;
    private long currentRequestTime;

    public Map<String, RedisCacheContext> getCache() {
        return cache;
    }

    public void setCache(Map<String, RedisCacheContext> cache) {
        this.cache = cache;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    public long getCurrentRequestTime() {
        return currentRequestTime;
    }

    public void setCurrentRequestTime(long currentRequestTime) {
        this.currentRequestTime = currentRequestTime;
    }

    @Override
    public String toString() {
        return "RedisClientContext{" +
                "cache=" + cache +
                ", command='" + command + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", expiry=" + expiry +
                ", currentRequestTime=" + currentRequestTime +
                '}';
    }
}
