package nefu.laboratory.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient(){
        Config config=new Config();
        config.useSingleServer()
                .setAddress("redis://120.46.159.231:6379")
                .setUsername("default")
                .setDatabase(9)
                .setPassword("2046");

        config.setCodec(new JsonJacksonCodec());//设置为JSON形式
        return Redisson.create(config);
    }
}
