package com.cn.springbootutil.common.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author: YZH
 * @create: 2020-03-13 17:01
 **/
public class JwtToken {
    public static String createToken(String userName,String passWord) {
        //当前时间
        Date date=new Date();
        Calendar nowTime=Calendar.getInstance();
        //设置超时时间
        nowTime.add(Calendar.HOUR,1);
        Date exprieTime = nowTime.getTime();
        DateFormat dateFormat=DateFormat.getDateInstance();
        System.out.println("超时时间"+dateFormat.format(exprieTime));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token="";
        try {
            token= JWT.create()
                    //header
                    .withHeader(map)
                    .withClaim("userId", userName)
                    .withClaim("passWord", passWord)
                    .withExpiresAt(exprieTime)
                    .withIssuedAt(date)
                    //加密
                    .sign(Algorithm.HMAC256("Savior"));
        }catch (Exception e){
           e.printStackTrace();
        }
        return token;
    }

    public static Map<String, Claim> verifyToken(String token,String key) throws IllegalArgumentException, UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key))
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        String userId = claims.get("userId").asString();
        System.out.println(userId);
        return claims;
    }

    public static void main(String[] args) throws Exception {
      String token = createToken("1","123456");
        System.out.println(token);


    }
}
