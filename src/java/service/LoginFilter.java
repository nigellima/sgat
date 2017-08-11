/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.LoginController;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucasmaia.huufma
 */
public class LoginFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginController loginController = (LoginController) ((HttpServletRequest) request).getSession().getAttribute("loginController");
        HttpServletResponse  res = (HttpServletResponse) response;
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        res.setDateHeader("Expires", 0); // Proxies.
        //System.out.println("prevent caching");
        if(loginController == null || !loginController.isLoggedIn()){
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) res).sendRedirect(contextPath + "/index.xhtml");
        }
        else {
            chain.doFilter(request, res);
        }
    }

    @Override
    public void destroy() {}
    
}
