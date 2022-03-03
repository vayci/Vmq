package com.vone.mq.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Red
 */
@Controller
public class MyErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        System.out.println(request.getMethod()+"=>"+request.getRequestURI());
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == 401){
            return "/401";
        }else if(statusCode == 404){
            return "/404";
        }else if(statusCode == 403){
            return "/403";
        }else{
            return "/500";
        }

    }
}
