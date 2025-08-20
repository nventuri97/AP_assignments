package uni.advanced_programming;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface XMLfield {
    String type ();
    //default value allows the parameter to be optional
    String name () default "";

}
