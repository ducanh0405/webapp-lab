package com.student.controller;

import com.student.util.CookieUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/theme")
public class ThemeController extends HttpServlet {

    private static final int ONE_YEAR = 365 * 24 * 60 * 60;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String set = request.getParameter("set");
        if (!"dark".equals(set) && !"light".equals(set)) {
            set = "light";
        }

        String path = CookieUtil.appCookiePath(request);
        CookieUtil.addCookie(response, "user_theme", set, ONE_YEAR, path, false);

        String redirect = request.getParameter("redirect");
        if (redirect == null || redirect.contains("..") || !redirect.startsWith("/")
                || redirect.startsWith("//") || redirect.length() > 200) {
            redirect = "/dashboard";
        }

        response.sendRedirect(request.getContextPath() + redirect);
    }
}
