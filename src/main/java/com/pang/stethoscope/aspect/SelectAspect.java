package com.pang.stethoscope.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class SelectAspect {
    @Pointcut("execution(* com.example.stethoscope_blog.controller..*.*(..))") //pointcut表达式
    public void executeService(){}

    @Before("executeService()") //注解一个before Advice
    public void doBeforeAdvice(JoinPoint joinPoint){
        //获取RequestAttributes
        RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();
        //从获取的获取RequestAttributes中获取HttpServletRequest
        HttpServletRequest request= (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        Enumeration<String> enumeration=request.getParameterNames();
        Map<String,String> paramMap=new HashMap<String,String>();
        while (enumeration.hasMoreElements()){
            String param=enumeration.nextElement();
            paramMap.put(param,request.getParameter(param));
        }
        System.out.println("请求的参数信息JSON格式为：");
        System.out.println(paramMap);
    }
}
