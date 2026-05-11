package com.student.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Static helpers for creating, reading, updating, and removing cookies.
 */
public final class CookieUtil {

    private CookieUtil() {
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) {
                return c.getValue();
            }
        }
        return null;
    }

    public static int getIntCookie(HttpServletRequest request, String name, int defaultValue) {
        String v = getCookieValue(request, name);
        if (v == null || v.isBlank()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(v.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static long getLongCookie(HttpServletRequest request, String name, long defaultValue) {
        String v = getCookieValue(request, name);
        if (v == null || v.isBlank()) {
            return defaultValue;
        }
        try {
            return Long.parseLong(v.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static void addCookie(HttpServletResponse response, String name, String value,
                                 int maxAgeSeconds, String path, boolean httpOnly) {
        Cookie c = new Cookie(name, value != null ? value : "");
        c.setMaxAge(maxAgeSeconds);
        c.setPath(path != null ? path : "/");
        c.setHttpOnly(httpOnly);
        response.addCookie(c);
    }

    public static void deleteCookie(HttpServletResponse response, String name, String path) {
        Cookie c = new Cookie(name, "");
        c.setMaxAge(0);
        c.setPath(path != null ? path : "/");
        c.setHttpOnly(true);
        response.addCookie(c);
    }

    /** URL-encode cookie value when it may contain spaces or special characters. */
    public static String encode(String raw) {
        if (raw == null) {
            return "";
        }
        return URLEncoder.encode(raw, StandardCharsets.UTF_8);
    }

    /** Cookie path scoped to the web application (works for ROOT and prefixed contexts). */
    public static String appCookiePath(HttpServletRequest request) {
        String cp = Objects.requireNonNullElse(request.getContextPath(), "");
        return cp.isEmpty() ? "/" : cp + "/";
    }
}
