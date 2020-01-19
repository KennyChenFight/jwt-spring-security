package kenny.home.jwtspringsecurity.lib;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Calendar;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JWTUtil {

    private String secretKey;
    private int lifeTime;

    public String Sign(String userId) {
        Claims claims = Jwts.claims();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, lifeTime);
        claims.setExpiration(calendar.getTime());
        claims.put("userId", userId);

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder().setClaims(claims).signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public String Verify(String token) {
        String userId;
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            userId = claims.get("userId").toString();
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }
}
