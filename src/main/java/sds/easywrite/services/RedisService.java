package sds.easywrite.services;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setValueWithExpirationSeconds(String key, Object value, Duration expirationSeconds) {
        redisTemplate.opsForValue().set(key, value, expirationSeconds);
    }

    public void setValueWithTimeUnit(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setValue(String prefix, String key, Object value) {
        redisTemplate.opsForValue().set(prefix + "-" + key, value);
    }

    public Object getValue(String prefix, String key) {
        return redisTemplate.opsForValue().get(prefix + "-" + key);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Set<String> getAllKeys() {
        return redisTemplate.keys("*");
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    public void deleteMultiKeys(List<String> keys) {
        redisTemplate.delete(keys);
    }

    public void pushToSet(String key, Object... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public Set<Object> getValueSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public Long getSizeSet(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    public void removeInSet(String key, Object... values) {
        redisTemplate.opsForSet().remove(key, values);
    }
}
