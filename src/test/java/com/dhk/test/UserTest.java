package com.dhk.test;

import com.dhk.dao.UserMapperDao;
import com.dhk.entity.User;
import com.dhk.vo.UserOrdersVo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    private static Logger logger = Logger.getLogger(UserTest.class);
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
    public void testSelectUserById(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapperDao userMapperDao = sqlSession.getMapper(UserMapperDao.class);
        User user = userMapperDao.selectUserById(10);
        System.out.println(user);
        sqlSession.close();
    }
    //一对多
    @Test
    public void testSelectUserOrdersByUserId(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapperDao userMapperDao = sqlSession.getMapper(UserMapperDao.class);
        User user = userMapperDao.selectUserOrdersByUserId(1);
        System.out.println(user);
        sqlSession.close();
    }
    //一对多
    @Test
    public void testSelectUserOrdersByUserId2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapperDao userMapperDao = sqlSession.getMapper(UserMapperDao.class);
        UserOrdersVo user = userMapperDao.selectUserOrdersByUserId2(1);
        System.out.println(user);
        sqlSession.close();
        System.out.println(user);

    }
    //测试二级缓存
    @Test
    public void testSelectUserByIdTwoCache(){
          SqlSession sqlSession = sqlSessionFactory.openSession();
          User user1 =null;
          try{
              UserMapperDao userMapperDao =sqlSession.getMapper(UserMapperDao.class);
              user1=userMapperDao.selectUserById(1);
              user1.setUserName("haha");
              System.out.println("1-----------走的是一级缓存，二级缓存命中率为0---------------");
              User user2 = userMapperDao.selectUserById(1);
              System.out.println("2-----------走的是一级缓存，二级缓存命中率为0---------------");
              userMapperDao.selectUserById(1);
              System.out.println("3-----------走的是一级缓存，二级缓存命中率为0---------------");
              userMapperDao.selectUserById(1);
              //一级缓存相等
              Assert.assertEquals("haha",user2.getUserName());
              Assert.assertEquals(user1,user2);
              Assert.assertSame("haha",user2.getUserName());
              Assert.assertSame(user1,user2);
              System.out.println(user1==user2);
          }finally {
              sqlSession.close();
          }
          System.out.println("开启新的sqlSession");
          sqlSession = sqlSessionFactory.openSession();
          try{
              UserMapperDao userMapperDao = sqlSession.getMapper(UserMapperDao.class);
              User user2 = userMapperDao.selectUserById(1);
              System.out.println(user2.getUserName());//应该是haha，从上一个sqlSession中填入的二级缓存
//              Assert.assertEquals(user1,user2);//应该不是一个，因为readOnly= false
              System.out.println(user1==user2);
              Assert.assertNotSame(user1,user2);
              User user3 = userMapperDao.selectUserById(1);
//              Assert.assertEquals(user2,user3);
              System.out.println(user2==user3);
              Assert.assertNotSame(user2,user3);

          }finally {
              sqlSession.close();
          }
        /**
         *一级和二级缓存都是：基于PerpetualCache 的 HashMap
         * 经测试，如果刚开始HashMap里面什么都没有，走的是一级缓存。
         * 当第二次sqlSession再查找同一个sql时，走的是二级缓存，因为HashMap里面有二级缓存的key了。
         */

    }
    @Test
    public void testSelectUserByUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapperDao userMapperDao = sqlSession.getMapper(UserMapperDao.class);
        User u = new User();
        u.setId(1);
        User result = userMapperDao.selectUserByUser(u);
        System.out.println(result);
        sqlSession.close();;
    }
    public static void main(String args[]){
        logger.error("asdf");
        User user1 = new User();
        user1.setUserName("abc");
        User user2 = new User();
        user2.setUserName("ac");
        Assert.assertEquals(user1,user2);
        System.out.println(user1==user2);
        //assertEquals比较内容和引用，assertSame比较引用地址。
    }
}
