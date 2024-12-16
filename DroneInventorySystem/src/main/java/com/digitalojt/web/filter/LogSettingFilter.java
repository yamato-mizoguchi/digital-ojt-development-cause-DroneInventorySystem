package com.digitalojt.web.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
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

	// IPアドレスを受け渡す文字列
    private static String CLIENT = "client";
    // 管理者IDを受け渡す文字列
    private static String ADMINID = "adminId";

    /**
     * 
     * @return 管理者IDの取得
     */
    public String getCurrentAdminId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // 管理者名（ID）を取得
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
            MDC.put(ADMINID, adminId);
            MDC.put(CLIENT, servletRequest.getRemoteAddr());
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
        	MDC.remove(ADMINID);
            MDC.remove(CLIENT);
        }
    }
}