package com.example.his.api.config.xss;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * ClassName:
 * Package: com.example.his.api.config.xss
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/9 22:15
 * @Version 1.0
 */
@WebFilter(urlPatterns = "/*") // 拦截所有请求
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 创建【代理人】请求对象
        XssHttpServletRequestWrapper wrapper = new XssHttpServletRequestWrapper(request);
        filterChain.doFilter(wrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
