package com.dhk.test;

import org.apache.ibatis.session.SqlSession;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * 实现动态代理类
 * @param <T>
 */
public class MyMapperProxy<T>  implements InvocationHandler {
    private Class<T>  personMapperDao;
    private SqlSession sqlSession ;
    public MyMapperProxy(Class<T>  personMapperDao,SqlSession sqlSession){
        this.personMapperDao = personMapperDao;
        this.sqlSession = sqlSession;
    }
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
//        HashMap<String,Object> hm = new HashMap<>();
//        hm.put("myid",8);
//        hm.put("myName","dhktest");
        System.out.println(objects.length);
        System.out.println("test:::"+personMapperDao.getCanonicalName()+"."+method.getName());
        Object obj = sqlSession.selectOne(personMapperDao.getCanonicalName()+"."+method.getName(),(Long)objects[0]);
        return obj;
    }
}
