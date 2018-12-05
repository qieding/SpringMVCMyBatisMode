package com.dhk.controller;

import com.dhk.entity.Person;
import com.dhk.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;
    @RequestMapping("/selectPerson")
    public String selectPerson(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        request.setCharacterEncoding("utf-8");
//        response.setCharacterEncoding("utf-8");
//        long personId = Long.parseLong(request.getParameter("id"));
//        Person person =personService.findPersonById(personId);
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        response.getWriter().write(mapper.writeValueAsString(person));
//        response.getWriter().close();
        System.out.println("sle");
        return "forward:getUserById?id=1&abc=25";
    }
    @RequestMapping("test")
    public String testMap(HttpServletRequest request, HttpServletResponse response) throws IOException{
                return "ok";
    }
    @RequestMapping("testAutoWireAndResource")
    @ResponseBody
    public Person testAutoWireAndResource(){
        Person person = personService.findPersonById(8l);
        person.setName("丁鸿凯");
        return person;
    }
}

