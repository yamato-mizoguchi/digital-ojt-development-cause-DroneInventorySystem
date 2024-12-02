package com.digitalojt.web.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * ログ出力用の診断コンテキストを設定するFilterクラス.
 */
public class LogSettingFilter implements Filter {

    String CLIENT = "client";
//    AdminInfo adminInfo = new AdminInfo();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public String getCurrentAdminId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // ユーザー名（ID）を取得
    }
    
    /**
     * 診断コンテキストを設定します.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
        	String adminId = getCurrentAdminId();
            MDC.put("adminId", adminId);
            MDC.put(CLIENT, servletRequest.getRemoteAddr());
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
        	MDC.remove("adminId");
            MDC.remove(CLIENT);
        }
//        try {
//            MDC.put("userId", adminInfo.getAdminId()); // ユーザーIDをMDCに設定
//            filterChain.doFilter(servletRequest, servletResponse);
//        } finally {
//            MDC.remove("userId"); // 処理後にMDCからユーザーIDを削除
//        }
    }

    @Override
    public void destroy() {

    }
}