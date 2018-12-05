package com.dhk.test;

import com.dhk.dao.OrdersMapperDao;
import com.dhk.entity.Orders;
import com.dhk.entity.User;
import com.dhk.vo.OrdersUserVo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrdersTest {
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
    public void testSelectOrdersById(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperDao ordersMapperDao =sqlSession.getMapper(OrdersMapperDao.class);
        Orders orders = ordersMapperDao.selectOrdersById(3);
        System.out.println(orders);
        sqlSession.close();

    }
    //测试一对一
    @Test
    public void testSelectOrdersUserByOrdersId(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperDao ordersMapperDao =sqlSession.getMapper(OrdersMapperDao.class);
        OrdersUserVo ordersUserVo = ordersMapperDao.selectOrdersUserByOrdersId(5);
        System.out.println(ordersUserVo);
        sqlSession.close();
    }
    //测试一对一
    @Test
    public void testSelectOrdersUserByOrdersId2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperDao ordersMapperDao =sqlSession.getMapper(OrdersMapperDao.class);
        OrdersUserVo ordersUserVo = ordersMapperDao.selectOrdersUserByOrdersId2(5);
        System.out.println(ordersUserVo);
        sqlSession.close();
    }
    //测试一对一
    @Test
    public void testSelectOrdersUserByOrdersId3(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperDao ordersMapperDao =sqlSession.getMapper(OrdersMapperDao.class);
        OrdersUserVo ordersUserVo = ordersMapperDao.selectOrdersUserByOrdersId3(5);
        System.out.println(ordersUserVo);
        sqlSession.close();
    }
    //测试一对一
    @Test
    public void testSelectOrdersUserByOrdersId4(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperDao ordersMapperDao =sqlSession.getMapper(OrdersMapperDao.class);
        OrdersUserVo ordersUserVo = ordersMapperDao.selectOrdersUserByOrdersId4(5);
        System.out.println(ordersUserVo);
        sqlSession.close();
    }
    //测试一对一
    @Test
    public void testSelectOrdersUserByOrdersId5(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperDao ordersMapperDao =sqlSession.getMapper(OrdersMapperDao.class);
        OrdersUserVo ordersUserVo = ordersMapperDao.selectOrdersUserByOrdersId5(5);
        System.out.println(ordersUserVo);
        sqlSession.close();
    }
    //测试一对一
    @Test
    public void testSelectOrdersUserByOrdersId6(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperDao ordersMapperDao =sqlSession.getMapper(OrdersMapperDao.class);
        OrdersUserVo ordersUserVo = ordersMapperDao.selectOrdersUserByOrdersId6(5);
        System.out.println("调用getUser方法实现懒加载");
        System.out.println(ordersUserVo.getUser().getUserName());
//        System.out.println(ordersUserVo);
        sqlSession.close();
    }
    //测试@Data对父类的影响，注解才会对父类影响
    @Test
    public void testDataSuper(){
        User user = new User();
        user.setUserName("dhk");
        OrdersUserVo ordersUserVo1 = new OrdersUserVo();
        ordersUserVo1.setId(12);
        ordersUserVo1.setNumber("asd");
        ordersUserVo1.setUser(user);
        OrdersUserVo ordersUserVo2 = new OrdersUserVo();
        ordersUserVo2.setId(12);
        ordersUserVo2.setNumber("as");
        ordersUserVo2.setUser(user);
        if(ordersUserVo1.equals(ordersUserVo2)){
            System.out.println(ordersUserVo1+"  eq: "+ordersUserVo2);
        }
    }
    //测试二级缓存ehcache
    @Test
    public void testSelectOrdersByIdEhcache(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperDao ordersMapperDao =sqlSession.getMapper(OrdersMapperDao.class);
        Orders orders = ordersMapperDao.selectOrdersById(3);
        System.out.println(orders);
        sqlSession.close();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        OrdersMapperDao ordersMapperDao2 =sqlSession2.getMapper(OrdersMapperDao.class);
        Orders orders2 = ordersMapperDao2.selectOrdersById(3);
        System.out.println(orders2);
        sqlSession2.close();

    }
}
