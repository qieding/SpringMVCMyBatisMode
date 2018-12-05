package com.dhk.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        StringBuffer url = httpServletRequest.getRequestURL();
        System.out.println(url+"    "+url.indexOf("login"));
        if(url.indexOf("login")>=0){
            System.out.println("包括login");
               return true;
        }
        HttpSession httpSession = httpServletRequest.getSession();
        String username = (String) httpSession.getAttribute("username");
        if(username!=null){
            System.out.println("用户名不为空");
                return true;
        }
        System.out.println("返回登录页");
        /**
         *  这里测试非常有意思：方便理解SpringMVC拦截器整个流程
         *  1、当Controller返回逻辑视图名时候，会重新执行拦截器的。
         *  2、如果上一个URL的前缀锁定现在forword另一个URLname后面的则会在前面基础上forward的。
         *  http://localhost:8080/SpringMVCMyBatis/logout    -1
         * 用户名不为空
         * http://localhost:8080/SpringMVCMyBatis/WEB-INF/views/error.html    -1
         * 返回登录页
         * http://localhost:8080/SpringMVCMyBatis/WEB-INF/views/loginPage    53
         * 包括login
         * http://localhost:8080/SpringMVCMyBatis/logout    -1
         * 返回登录页
         * http://localhost:8080/SpringMVCMyBatis/loginPage    39
         * 包括login
         * http://localhost:8080/SpringMVCMyBatis/WEB-INF/views/login.html    53
         */
//        httpServletRequest.getRequestDispatcher("loginPage").forward(httpServletRequest,httpServletResponse);
          httpServletRequest.getRequestDispatcher("/WEB-INF/views/login.html").forward(httpServletRequest,httpServletResponse);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
