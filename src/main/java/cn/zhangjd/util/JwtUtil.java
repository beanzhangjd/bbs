package cn.zhangjd.util;

import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;


public class JwtUtil {
	public static String setToken(String name,String[] user) {
		Map<String, Object> map = new HashMap<String, Object>();  
        map.put("alg", "HS256");  
        map.put("typ", "JWT");  
        String token = JWT.create()  
                .withHeader(map)//header  
                .withArrayClaim(name, user)//payload  
                .sign(Algorithm.HMAC256("secret"));//加密  
        return token;  
	}
	public static String[] checkToken(String name,String token) {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret"))  
                .build();   
            DecodedJWT jwt = verifier.verify(token);  
            Map<String, Claim> claims = jwt.getClaims();  
            return claims.get(name).asArray(String.class);  
	}

}
