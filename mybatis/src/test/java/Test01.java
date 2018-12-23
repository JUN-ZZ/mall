import com.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author jun
 * @date 2018/12/22
 */
public class Test01 {

    SqlSession session = null;

    @Before
    public void before() throws IOException {

        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();

    }

    @Test
    public void test(){

        List<User> list = session.selectList("com.mybatis.domain.UserMapper.querAll");
        System.out.println(Arrays.toString(list.toArray()));


    }









}
