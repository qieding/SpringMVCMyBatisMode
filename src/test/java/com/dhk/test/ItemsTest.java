package com.dhk.test;

import com.dhk.entity.Items;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ItemsTest {
    private SqlSessionFactory sqlSessionFactory;
    // 此方法是在执行testFindUserById之前执行
    @Before
    public void setUp() throws Exception {
        // 创建sqlSessionFactory

//        // mybatis配置文件
//        String resource = "config/mybatis/sqlMapConfig.xml";
//        // 得到配置文件流
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//
//        // 创建会话工厂，传入mybatis的配置文件信息
//        sqlSessionFactory = new SqlSessionFactoryBuilder()
//                .build(inputStream);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/spring/spring-mybatis.xml");
        sqlSessionFactory = (SqlSessionFactory)context.getBean("sqlSessionFactory");
    }
    @Test
    public void testSelectItemsById(){
        //测试selectOne方法，没有接口和xml关联的情况下，单纯的Mybatis解析xml。
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Items items= sqlSession.selectOne("xxx.xx.selectItemsById",1);
        System.out.println(items);
        sqlSession.close();
    }
}
