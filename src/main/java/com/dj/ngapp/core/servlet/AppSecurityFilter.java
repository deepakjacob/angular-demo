package com.dj.ngapp.core.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.dj.ngapp.core.UserUtil;
import com.dj.ngapp.core.model.User;
import com.google.inject.Singleton;

/**
 * All requests will be intercepted and checked for mandatory credentials.
 *
 * @author Deepak Jacob
 */
@Singleton
public class AppSecurityFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        
        
        UserUtil.USER.set(new User("an-acual-username-validated-against-database"));
        filterChain.doFilter(servletRequest, servletResponse);
        UserUtil.USER.remove() ;
    }

    @Override
    public void destroy() {
    }
}
