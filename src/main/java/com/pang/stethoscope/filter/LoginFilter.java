package com.pang.stethoscope.filter;


import com.pang.stethoscope.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/login/login",filterName = "LogFilter")
public class LoginFilter implements Filter {

    @Autowired
    LogMapper loggeer;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        //TODO: 登录日志...这里是不是应该写权限校验而不是登录日志
        //loggeer.addLog("admin","login");
    }

    @Override
    public void destroy() {

    }
}
