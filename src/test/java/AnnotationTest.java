import java.lang.annotation.*;
import java.security.acl.AclNotFoundException;

/**
 * @author jun
 * @date 2018/12/17
 */
//能被子类继承Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface AnnotationTest {
    String value() default "可以被继承";
}

//能被子类继承Inherited
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
 @interface No{
    String value() default "不能被继承";
}

@No
@AnnotationTest
class A{
}



class B extends A{

    public static void main(String[] args){
        Class clz = B.class;
        Annotation[] as = clz.getAnnotations();
        for(Annotation a:as){
            System.out.println(a);
        }
    }

}


