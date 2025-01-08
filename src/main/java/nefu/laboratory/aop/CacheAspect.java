package nefu.laboratory.aop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RBucket;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.stereotype.Component;

import javax.xml.datatype.Duration;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Component
@Aspect
@RequiredArgsConstructor
public class CacheAspect {

    private final RedissonClient redissonClient;

    private final ObjectMapper objectMapper;

    @Pointcut("@annotation(Cachectx)")
    public void cacheableMethodPointcut() {}

    @Around("cacheableMethodPointcut()")
    public Object cacheableMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Cachectx cachectx = method.getAnnotation(Cachectx.class);

        long expireTime = cachectx.expireTime();

        String pKey=cachectx.key();

        String key = cachectx.table()+":"+resolveKey(joinPoint,pKey);

        RBucket<Object> bucket = redissonClient.getBucket(key, JsonJacksonCodec.INSTANCE);

        // 尝试从缓存中获取数据
        Object result = bucket.get();

        if (result!= null) {

            return result;
        }

        // 未命中缓存，执行方法并将结果存入缓存
        result = joinPoint.proceed();
        if (result!= null) {
            bucket.set(result);
            //设置过期时间
            Instant instant = Instant.now().plusSeconds(expireTime);
            bucket.expire(instant);
        }
        return result;
    }
    private String resolveKey(ProceedingJoinPoint joinPoint, String key) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < parameters.length; i++) {
            key = key.replace("#" + parameters[i].getName(), Objects.toString(args[i]));
        }
        return key;
    }
}
