package com.dhk.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//全局异常处理器
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        CustomExceptionBean customExceptionBean =null;
        if(e instanceof CustomExceptionBean){
            customExceptionBean =(CustomExceptionBean) e;
        }else{
            customExceptionBean = new CustomExceptionBean("未知错误");
        }
        System.out.println("执行exception");
        String message = customExceptionBean.getMessage();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",message);
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
