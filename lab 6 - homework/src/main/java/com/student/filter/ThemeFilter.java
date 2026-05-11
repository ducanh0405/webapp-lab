package com.student.filter;

import com.student.util.CookieUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Exposes {@code uiTheme} request attribute from {@code user_theme} cookie (light/dark).
 */
@WebFilter(filterName = "ThemeFilter", urlPatterns = {"/*"})
public class ThemeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String theme = CookieUtil.getCookieValue(req, "user_theme");
        if (!"dark".equals(theme) && !"light".equals(theme)) {
            theme = "light";
        }
        req.setAttribute("uiTheme", theme);
        chain.doFilter(request, response);
    }
}
