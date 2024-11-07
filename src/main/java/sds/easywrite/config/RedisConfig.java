package sds.easywrite.config;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.database}")
    private int redisDatabase;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);
        configuration.setDatabase(redisDatabase);
        if (!Strings.isNullOrEmpty(redisPassword)) {
            configuration.setPassword(redisPassword);
        }
        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
