package com.bhaskar.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bhaskar.model.MyUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Date;

import static java.util.Collections.emptyList;

public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000_000; // token lasts for 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Token";
    static final String HEADER_STRING = "Authorization";

    //creation of token
    static void addAuthentication(HttpServletResponse httpServletResponse, String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        httpServletResponse.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    //token validation
    static Authentication getAuthentication(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(HEADER_STRING);

        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }

}
