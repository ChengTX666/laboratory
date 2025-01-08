package nefu.laboratory.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.Instant;
import java.util.Objects;

@Component
@Aspect
@RequiredArgsConstructor
public class UpdateAspect {

    private final RedissonClient redissonClient;

    @Pointcut("@annotation(Updatectx)")
    public void myPointcut() {}

    @Around("myPointcut()")
    public void updateCache(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Updatectx updatectx=method.getAnnotation(Updatectx.class);

        String table = updatectx.table();
        String pkey = updatectx.key();
        String returnMethod = updatectx.returnMethod();
        long expireTime = updatectx.expireTime();

        String eLvalue = resolveKey(joinPoint, pkey);


        String key = table+":"+ eLvalue;

        RBucket<Object> bucket = redissonClient.getBucket(key, JsonJacksonCodec.INSTANCE);

//        bucket.delete();

        //执行更新/删除/添加方法
        joinPoint.proceed();

        if (!returnMethod.isEmpty()) {
            // 获取目标对象
            Object target = joinPoint.getTarget();
            Class<?> parameterType=String.class;
            // 获取要调用的方法
            Method methodToInvoke = target.getClass().getMethod(returnMethod,parameterType);

            // 调用方法
            Object invoke = methodToInvoke.invoke(target,eLvalue);

            bucket.set(invoke);
            //设置过期时间
            Instant instant = Instant.now().plusSeconds(expireTime);
            bucket.expire(instant);
        }
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
