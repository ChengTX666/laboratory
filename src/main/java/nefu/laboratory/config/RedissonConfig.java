package nefu.laboratory.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient(){
        Config config=new Config();
        config.useSingleServer()
                .setAddress("redis://120.46.159.231:6379")
                .setUsername("default")
                .setDatabase(9)
                .setPassword("2046");
        Codec codec=new JsonJacksonCodec();
        config.setCodec(codec);
        return Redisson.create(config);
    }
}
