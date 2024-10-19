package com.isteyft.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "isteyft";
    private static Long expire = 43200000L;// 12 hours in milliseconds

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
    /**
     * 验证JWT令牌的有效性
     * @param jwt JWT令牌
     * @return 是否有效
     */
    public static boolean validateJwt(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey(signKey)
                    .parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从JWT令牌中获取用户名
     * @param jwt JWT令牌
     * @return 用户名
     */
    public static String getUsernameFromJwt(String jwt) {
        Claims claims = parseJWT(jwt);
        return claims.getSubject();
    }
}
