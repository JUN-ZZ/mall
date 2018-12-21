package com.mybatis.test;

import com.mybatis.domain.User;
import com.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author jun
 * @date 2018/12/21
 */
public class Test2 {

    public static void main(String[] args) throws IOException {
        //1.利用mybatis提供的Resources类读取mybatis核心配置文件
        //单纯利用配置文件的方式进行
        InputStream in = Resources.getResourceAsStream("sqlMybatisInterface.xml");
        //2.根据配置文件创建sqlsessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.创建sqlSession
        SqlSession session = factory.openSession();
//        //4.执行操作
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> list = userMapper.queryUserAll();
        System.out.println(Arrays.toString(list.toArray()));
        String username = userMapper.queryUsernameByAge(18);
        System.out.println(username);

    }


}
