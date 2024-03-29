package kz.kalybayevv.Bank.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenHelper {
    private static final String key =
            "UEBReVMyNFQoMg==KUlDJWM2NHtTQihndz5WNQ==dyg+TXksOjpLM2NcajV4cGhrNC8yKCQ3LnBKXThkLilvIXhbdUYoUEt6LXk=";
    private static final Integer expirationDay = 7;

    public static String generateJwt(String phoneNumber, String cardNumber) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        return Jwts.builder().claim("phone_number", phoneNumber)
                .claim("card_number", cardNumber)
                .claim("authorities",
                        grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date()).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(expirationDay)))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(key))).compact();
    }

    public static Claims decodeJwt(String jwt) {
        return Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(key))).parseClaimsJws(jwt).getBody();
    }
}
