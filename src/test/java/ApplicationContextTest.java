import com.mall.domain.Prod;
import com.mall.domain.User;
import com.mall.exception.MsgException;
import com.mall.service.ProdService;
import com.mall.service.UserService;
import com.mall.service.UserServiceImpl;
import com.mall.service.UserServiceImplProxy;
import com.mall.util.MD5Utils;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author jun
 * @date 2018/12/17
 */


public class ApplicationContextTest {

//    public static void main(String[] args){
//
//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext("applicationContext.xml");
//        User user = context.getBean(User.class);
//
//        System.out.println(user);
//
//    }

//    @Test
    public static void main(String[] args){

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        ProdService service = (ProdService)context.getBean("prodService");

        try {
            List<Prod> list = service.listAllProd();
            System.out.println(list);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
