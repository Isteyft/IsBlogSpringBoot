package com.isteyft.Interceptor;

import com.isteyft.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.util.UrlPathHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final UrlPathHelper urlPathHelper = new UrlPathHelper();
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            logger.info("OPTIONS请求，放行");
            // 设置响应头，允许跨域请求
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
            return true;
        }
        // 判断请求路径是否存在
        if (!request.getServletPath().startsWith("/v1/") && !request.getServletPath().startsWith("/v2/") && !request.getServletPath().equals("/login")) {
            return handleNotFound(request, response);
        }
        System.out.println("a");
        // 判断是否需要验证
        if (needsAuthentication(request)) {
            String token = extractTokenFromHeader(request);
            if (JwtUtils.validateJwt(token)) {
                return true;
            }
//        if (needsAuthentication(request)) {
//            String token = request.getHeader("token");
//            System.out.println(token);
//            System.out.println(JwtUtils.validateJwt(token));
//            if (JwtUtils.validateJwt(token)) {
//                return true;
//            }
            // JWT验证失败，返回错误响应
            return handleInvalidToken(request, response);
        }

        // 不需要验证的请求，直接放行
        return true;
    }
    private String extractTokenFromHeader(HttpServletRequest request) {
        String headerValue = request.getHeader("Authorization");
        if (headerValue != null && headerValue.startsWith("Bearer ")) {
            return headerValue.substring(7); // 移除 "Bearer " 前缀
        }
        return null;
    }
    private boolean handleNotFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置响应的状态码为 Not Found (404)
        response.setStatus(HttpStatus.NOT_FOUND.value());
        // 设置响应的内容类型为JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // 设置响应字符编码为UTF-8
        response.setCharacterEncoding("UTF-8");
        // 写入错误信息
        response.getWriter().write("{\"code\":404, \"message\":\"路径不存在\"}");
        // 关闭流
        response.getWriter().close();
        return false;
    }
    private boolean handleInvalidToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置响应的状态码为未授权（401）
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        // 设置响应的内容类型为JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // 设置响应字符编码为UTF-8
        response.setCharacterEncoding("UTF-8");
        // 写入错误信息
        response.getWriter().write("{\"code\":401, \"message\":\"未登录或认证过期\"}");
        // 关闭流
        response.getWriter().close();
        return false;
    }

    private boolean needsAuthentication(HttpServletRequest request) {
        // 定义需要验证的路径
        String[] pathsToInclude = { "/v2/**" }; // 只有 /v2/** 需要验证

        String lookupPath = urlPathHelper.getLookupPathForRequest(request);
        for (String path : pathsToInclude) {
            if (antPathMatcher.match(path, lookupPath)) {
                return true; // 需要验证的路径
            }
        }
        return false; // 不需要验证的路径
    }
}