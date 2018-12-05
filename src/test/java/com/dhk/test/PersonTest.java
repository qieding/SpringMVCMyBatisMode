package com.dhk.test;

import com.dhk.dao.PersonMapperDao;
import com.dhk.entity.Person;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonTest {
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

    //用户信息的综合 查询
    @Test
    public void testFindUser() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        PersonMapperDao personMapper = sqlSession.getMapper(PersonMapperDao.class);
        Person person =  personMapper.findPersonById(1l);
        Person person1 = sqlSession.selectOne("com.dhk.dao.PersonMapperDao.findPersonById",1l);
        System.out.println(person1);
        //List<UserCustom> list = userMapper.findUserList(userQueryVo);

        System.out.println(person);
    }
    //用户信息插入，返回自增主键
    @Test
    public void testInsertPersonReturnKey()throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapperDao personMapperDao = sqlSession.getMapper(PersonMapperDao.class);
        Person person = new Person();
        person.setEmail("djaaa@qq.com");
        person.setName("dhktest");
        person.setStatus(123);
        int result = personMapperDao.insertPersonReturnKey(person);
        Assert.assertEquals(1,result);
        Assert.assertNotNull(person.getId());
        System.out.println(person.getId());
    }
    //用户信息插入，返回自增主键
    @Test
    public void testInsertSelectKey()throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapperDao personMapperDao = sqlSession.getMapper(PersonMapperDao.class);
        Person person = new Person();
        person.setEmail("djaaaqq@qq.com");
        person.setName("dhktest");
        person.setStatus(123);
        int result = personMapperDao.insertSelectKey(person);
        Assert.assertEquals(1,result);
        Assert.assertNotNull(person.getId());
        System.out.println(person.getId());
    }
    //用户信息更改，返回自增主键
    @Test
    public void testUpdatePersonByName()throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapperDao personMapperDao = sqlSession.getMapper(PersonMapperDao.class);
        Person person = new Person();
        person.setId(15l);
        person.setEmail("djaaaqq@qq.com");
        person.setName("dhktestdhkdhk");
        person.setStatus(123);
        int result = personMapperDao.updatePersonByName(person);
        Assert.assertEquals(1,result);
        Assert.assertEquals("dhktestdhkdhk",personMapperDao.findPersonById(15l).getName());
        System.out.println(person.getId());
    }
    //用户信息更改，返回自增主键
    @Test
    public void testDeletePersonById()throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapperDao personMapperDao = sqlSession.getMapper(PersonMapperDao.class);
//        int result = personMapperDao.deletePersonById(15l);=
        Person person = new Person();
        person.setId(13l);
        int result = personMapperDao.deletePersonById(person);
        Assert.assertEquals(1,result);

        sqlSession.close();
    }
    //用户多参数查询
    @Test
    public void testSelectPersonByIdAndName()throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapperDao personMapperDao = sqlSession.getMapper(PersonMapperDao.class);
//        int result = personMapperDao.deletePersonById(15l);=
        Person person = new Person();
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("myid",10);
        hm.put("myName","dhktest");
        person = personMapperDao.selectPersonByIdAndName(hm);
//        person = personMapperDao.selectPersonByIdAndName2("dhktest",8l);
        System.out.println(person);

        sqlSession.close();
    }
    //动态代理实现查询
    @Test
    public void testProxy()throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        PersonMapperDao personMapperDao = sqlSession.getMapper(PersonMapperDao.class);
//        int result = personMapperDao.deletePersonById(15l);
        MyMapperProxy myMapperProxy = new MyMapperProxy(PersonMapperDao.class,sqlSession);
        PersonMapperDao personMapperDaoProxy = (PersonMapperDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{PersonMapperDao.class},
                myMapperProxy
        );
        Person person = new Person();
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("myid",8);
        hm.put("myName","dhktest");
        person = personMapperDaoProxy.findPersonById(8l);
//        person = personMapperDao.selectPersonByIdAndName2("dhktest",8l);
        System.out.println(person);

        sqlSession.close();
    }
    //测试多参数
    @Test
    public void testFindPersonBySQL()throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapperDao personMapperDao = sqlSession.getMapper(PersonMapperDao.class);
//        int result = personMapperDao.deletePersonById(15l);
        Person person =new Person();
        List<Person> persons =null;
        persons = personMapperDao.findPersonBySQL(person);
        System.out.println("1:-------------"+persons);
        person.setName("dhk");
        persons = personMapperDao.findPersonBySQL(person);
        System.out.println("2:-------------"+persons);
        person = new Person();
        person.setId(8l);
        persons = personMapperDao.findPersonBySQL(person);
        System.out.println("3:-------------"+persons);
        person = new Person();
        person.setId(8l);
        person.setName("d");
        persons = personMapperDao.findPersonBySQL(person);
        System.out.println("4:-------------"+persons);
//        person = personMapperDao.selectPersonByIdAndName2("dhktest",8l);

        sqlSession.close();
    }
    //测试多参数,where标签
    @Test
    public void testFindPersonBySQL2()throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapperDao personMapperDao = sqlSession.getMapper(PersonMapperDao.class);
//        int result = personMapperDao.deletePersonById(15l);
        Person person =new Person();
        List<Person> persons =null;
        persons = personMapperDao.findPersonBySQL2(person);
        System.out.println("1:-------------"+persons);
        person.setName("dhk");
        persons = personMapperDao.findPersonBySQL2(person);
        System.out.println("2:-------------"+persons);
        person = new Person();
        person.setId(8l);
        persons = personMapperDao.findPersonBySQL2(person);
        System.out.println("3:-------------"+persons);
        person = new Person();
        person.setId(8l);
        person.setName("d");
        persons = personMapperDao.findPersonBySQL2(person);
        System.out.println("4:-------------"+persons);
//        person = personMapperDao.selectPersonByIdAndName2("dhktest",8l);

        sqlSession.close();
    }
    //用户信息的综合 查询
    @Test
    public void testFindUserList() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        PersonMapperDao personMapper = sqlSession.getMapper(PersonMapperDao.class);
        List<Person> person =  personMapper.findPersonByName("adsf");
        //List<UserCustom> list = userMapper.findUserList(userQueryVo);

        System.out.println(person);
    }
    //用户信息的综合 查询
    @Test
    public void testUpdatePersonByIdSelective() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        PersonMapperDao personMapper = sqlSession.getMapper(PersonMapperDao.class);
        Person person = new Person();
        person.setId(81l);
        person.setName("hollowood");
        int result =  personMapper.updatePersonByIdSelective(person);
        //List<UserCustom> list = userMapper.findUserList(userQueryVo);
        sqlSession.close();
//        Assert.assertEquals(1,result);
        System.out.println(result);
    }
    //用户信息的综合 查询
    @Test
    public void testUpdatePersonByIdSelective2() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        PersonMapperDao personMapper = sqlSession.getMapper(PersonMapperDao.class);
        Person person = new Person();
        person.setId(8l);
        person.setName("hollowoo");
        int result =  personMapper.updatePersonByIdSelective2(person);
        //List<UserCustom> list = userMapper.findUserList(userQueryVo);
        sqlSession.close();
//        Assert.assertEquals(1,result);
        System.out.println(result);
    }
    //用户信息的综合 查询
    @Test
    public void testUpdatePersonByIdSelective3() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        PersonMapperDao personMapper = sqlSession.getMapper(PersonMapperDao.class);
        Person person = new Person();
        person.setId(8l);
        person.setName("hollowoodd");
        int result =  personMapper.updatePersonByIdSelective3(person);
        //List<UserCustom> list = userMapper.findUserList(userQueryVo);
        sqlSession.close();
//        Assert.assertEquals(1,result);
        System.out.println(result);
    }
    //动态sql实现insert
    @Test
    public void testInsertPersonBySQL() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        PersonMapperDao personMapper = sqlSession.getMapper(PersonMapperDao.class);
        Person person = new Person();
//        person.setId(81l);
        person.setName("hollowood");
        person.setEmail("asd@dhk.com");
        int result =  personMapper.insertPersonBySQL(person);
        //List<UserCustom> list = userMapper.findUserList(userQueryVo);
        sqlSession.close();
//        Assert.assertEquals(1,result);
        System.out.println(result);
    }
    //动态sql choose
    @Test
    public void testSelectPersonByIdOrName() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        PersonMapperDao personMapper = sqlSession.getMapper(PersonMapperDao.class);
        Person person = new Person();
//        person.setId(81l);
        person.setId(8l);
        person.setName("zzz");
        person.setEmail("asd@dhk.com");
        Person result =  personMapper.selectPersonByIdOrName(person);
        //List<UserCustom> list = userMapper.findUserList(userQueryVo);
        sqlSession.close();
//        Assert.assertEquals(1,result);
        System.out.println(result);
    }
    //动态sql foreach
    @Test
    public void testSelectPersonsForeachById() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        PersonMapperDao personMapper = sqlSession.getMapper(PersonMapperDao.class);
        ArrayList<Long> al = new ArrayList<Long>();
        al.add(8l);al.add(1l);al.add(2l);al.add(5l);
        List<Person> result =  personMapper.selectPersonsForeachById(al);
        //List<UserCustom> list = userMapper.findUserList(userQueryVo);
        sqlSession.close();
//        Assert.assertEquals(1,result);
        System.out.println(result);
    }
    //动态sql foreach
    @Test
    public void testSelectPersonsForeachMapById() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        PersonMapperDao personMapper = sqlSession.getMapper(PersonMapperDao.class);
        HashMap<Long,Long> mymp = new HashMap<>();
        mymp.put(1l,5l);mymp.put(2l,8l);
        List<Person> result =  personMapper.selectPersonsForeachMapById(mymp);
        //List<UserCustom> list = userMapper.findUserList(userQueryVo);
        sqlSession.close();
//        Assert.assertEquals(1,result);
        System.out.println(result);
    }
    //动态sql foreach P
    @Test
    public void testInsertPersonsForeachList() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        PersonMapperDao personMapper = sqlSession.getMapper(PersonMapperDao.class);
        ArrayList<Person> al = new ArrayList<>();
        Person p1 = new Person();
        p1.setName("zhu1oma");
        p1.setEmail("zh1u11112111oma3@qq.com");
        p1.setStatus(11);
        Person p2 = new Person();
        p2.setName("zhu1qoma2");
        p2.setEmail("zhu1q111o2111m1a231@qq.com");
        p2.setStatus(112);
        al.add(p1);
        al.add(p2);
        int result =  personMapper.insertPersonsForeachList(al);
        //List<UserCustom> list = userMapper.findUserList(userQueryVo);
        sqlSession.close();
//        Assert.assertEquals(1,result);
        System.out.println(al);
    }
    //动态sql foreach P
    @Test
    public void testSelectPersonByEmail() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        PersonMapperDao personMapper = sqlSession.getMapper(PersonMapperDao.class);
        Person person = new Person();
//        String args = "%' and 1=1 and '%'='";
        String args = "% and 1=1 and '%'='";
        person.setEmail(args);
        List<Person> persons =  personMapper.selectPersonByEmail(person);
        //List<UserCustom> list = userMapper.findUserList(userQueryVo);
        sqlSession.close();
//        Assert.assertEquals(1,result);
        System.out.println(persons);
    }
    //用户信息的查询，一级缓存测试
    @Test
    public void testFindUserOneCache() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        Person person1 = null;
        PersonMapperDao personMapper = sqlSession.getMapper(PersonMapperDao.class);
        person1 =  personMapper.findPersonById(1l);
        person1.setName("asdfasdf");
        Person person2 =  personMapper.findPersonById(1l);
        System.out.println(person2);
        System.out.println(person1==person2);
        sqlSession.close();
       System.out.println("开启新的sqlSession");
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        PersonMapperDao personMapper2 = sqlSession2.getMapper(PersonMapperDao.class);
        Person person3 =  personMapper2.findPersonById(1l);
        System.out.println(person3);
        System.out.println(person1==person3);
        sqlSession2.close();

    }
}