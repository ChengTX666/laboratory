package nefu.laboratory.config;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@EnableCaching
public class MyCacheConfig {
    @Bean
    public CacheManager cacheManager() {

        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Cache course = new ConcurrentMapCache("course");
        Cache lab = new ConcurrentMapCache("lab");
//        Cache reservation_t=new ConcurrentMapCache("reservation_t");
//        Cache reservation_l=new ConcurrentMapCache("reservation_l");
        cacheManager.setCaches(Arrays.asList(course, lab));
        return cacheManager;
    }



//    @Bean
//    public CacheManager cacheManager() {
//        Map<String, CacheConfig> map = new HashMap<>();
//
//        map.put("testt",new CacheConfig(30, 10*60));
//        return new RedissonSpringCacheManager(redissonClient,map);
//    }


        // 配置Redis缓存管理器
//        @Bean
//        public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//            // 缓存配置，设置缓存过期时间等
//            Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
//            cacheConfigurations.put("userCache", RedisCacheConfiguration.defaultCacheConfig()
//                    .entryTreaty(Duration.ofMinutes(10)) // 设置userCache缓存过期时间为10分钟
//                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())));
//            cacheConfigurations.put("productCache", RedisCacheConfiguration.defaultCacheConfig()
//                    .entryTreaty(Duration.ofHours(1)) // 设置productCache缓存过期时间为1小时
//                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())));
//
//            return RedisCacheManager.builder(redisConnectionFactory)
//                    .initialCacheNames(cacheConfigurations.keySet())
//                    .withInitialCacheConfigurations(cacheConfigurations)
//                    .build();
//        }
}
