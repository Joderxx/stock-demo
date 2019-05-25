package admin.core.demo.aopAnnotation;

import java.lang.annotation.*;

@Target({
        ElementType.METHOD,
        ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
    Class value() default Visitor.class;
}
