package com.dhk.dao;

import com.dhk.entity.Person;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PersonMapperDao {
    Person findPersonById(Long id);
    List<Person> findPersonByName(String name);
    int insertPersonReturnKey(Person person);
    int insertSelectKey(Person person);
    int updatePersonByName(Person person);
    int deletePersonById(Long id);
    //重载
    int deletePersonById(Person person);
    //map
    Person selectPersonByIdAndName(@Param("asdf") Map<String,Object> myHashMap);
    Person selectPersonByIdAndName2(@Param("myName") String name,@Param("myid") Long id);
    //动态SQL模糊查找
    List<Person> findPersonBySQL(Person person);
    List<Person> findPersonBySQL2(Person person);
    int updatePersonByIdSelective(Person person);
    int updatePersonByIdSelective2(Person person);
    int updatePersonByIdSelective3(Person person);
    int insertPersonBySQL(Person person);
    //choose-when-otherwise使用,name也唯一
    Person selectPersonByIdOrName(@Param("myperson") Person person);
    //foreach
    List<Person> selectPersonsForeachById(List<Long> mylist);
    List<Person> selectPersonsForeachMapById(@Param("mymP") Map myMap);
    int insertPersonsForeachList(List<Person> persons);
    List<Person> selectPersonByEmail(Person p);
}
