import com.mall.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jun
 * @date 2018/12/17
 */


public class ApplicationContextTest {

    public static void main(String[] args){

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = context.getBean("user",User.class);

        System.out.println(user);

    }


}
