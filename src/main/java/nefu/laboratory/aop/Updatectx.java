package nefu.laboratory.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Updatectx {
    String table() default "";
    String key() default "";
    String returnMethod() default "";
    long expireTime() default 60;
}
