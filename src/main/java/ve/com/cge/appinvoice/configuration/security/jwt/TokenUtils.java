
package ve.com.cge.appinvoice.configuration.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


/**
 * TokenUtils provide tools for tokens 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Feb 15, 2024
 */
public class TokenUtils {
    
    private static final String ACCESS_TOKEN_SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
    private static final long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;
    
    public static String createToken(String name, String username) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;    
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        Map<String, Object> extra = new HashMap<>();
        extra.put("name", name);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET_KEY.getBytes()))
                .compact();       
    }
    
    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String username = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        } catch (JwtException ex) {
            return null;
        }
    }
    
}
