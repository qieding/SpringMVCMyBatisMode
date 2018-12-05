package com.dhk.controller;

import com.dhk.entity.User;
import com.dhk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/getUserById"
//            ,produces = {"text/html;charset=UTF-8;"}//可以解决返回中文乱码问题，根据request中Accept属性决定返回类型
            )
    @ResponseBody
    public  String getUserById(@RequestParam("id") Integer id,Model model) throws Exception{
        Map map = model.asMap();
        System.out.println("在ModelAttribute中添加的abc的值："+map.get("string"));
        User user  = userService.getUserByUserId(id);
        System.out.println(user.toString()+"------");
        return "丁鸿凯";
    }
    //1----应用在有返回值的方法上,每次调用Controller其他方法会执行，将返回值放入到model。
    @ModelAttribute
    public String testModelAttribute(@RequestParam(value = "abc",required = true) Integer id)throws Exception{
        User user =userService.getUserByUserId(id);
        System.out.println("test ModelAttribute修饰方法，每执行一次controller其他方法都会执行一次该方法");
        //将字符串string和test:user.toString作为key、value对放入Model（可以看做Map）中。
        return "test:"+user.toString();
    }
    //1----应用在没有返回值的方法上，每次调用Controller其他方法会执行，将值插入到Model中。
    @ModelAttribute
    public void testModelAttribute2(@RequestParam(value = "abc2",required = false) Integer abc,Model model)throws Exception{
        model.addAttribute("abc2","dinghong");
    }
    //2----应用在方法的参数上，从前面的model中提取出值
    @RequestMapping("testModelAttributeGetValue")
    @ResponseBody
    public String testModelAttribute3(@ModelAttribute(value = "string") String abc,
                                      @ModelAttribute(value = "abc2") String abc2)throws Exception{
        return abc+"\r\n"+abc2;
    }
    //3----应用在方法上并使用了RequestMapping,相当于将name加入到了model中，
    // 返回Model数据到ok这个界面。
    @RequestMapping("ok")
    @ModelAttribute("haha")
    public String testModelAttribute4(@RequestParam(value = "name") String name)throws Exception{
        return name;
    }

}
