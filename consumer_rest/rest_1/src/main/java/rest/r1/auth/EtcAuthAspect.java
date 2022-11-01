package rest.r1.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Aspect
@Component
@Slf4j
public class EtcAuthAspect {
    @Value("${etcAuth.secretKey:}") private String secretKey;
    @Value("${etcAuth.tokenName:etc_token}") private String tokenName;
    @Value("${etcAuth.rolePrefix:}") private String rolePrefix;
    @Value("${etcAuth.roleAdmin:}") private String roleAdmin;
    @Value("${etcAuth.roleAny:*}") private String roleAny;
    @Value("${etcAuth.rolePublic:**}") private String rolePublic;

    @Around("@annotation(etcAuth)")
    public Object trace(ProceedingJoinPoint joinPoint, EtcAuth etcAuth) throws Throwable {
        if (isEmpty(secretKey))
            throw new Exception("Secret Key not found. Add property \"etcAuth.secretKey\" to app config.");
        final TokenUser user;
        try {
            user = getUserFromToken();
        } catch (Exception e) {
            log.error("Authorization error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        if (!checkRole(user, etcAuth.roles()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        final Object[] argsToModify = joinPoint.getArgs();
        for (int i = 0; i < argsToModify.length; i++) {
            if (argsToModify[i].getClass().equals(TokenUser.class))
                argsToModify[i] = user;
        }
        return joinPoint.proceed(argsToModify);
    }

    private boolean checkRole(TokenUser user, String[] rolesRequired) {
        final List<String> capitalizedRolesRequiredList = Arrays.stream(rolesRequired).map(String::toUpperCase).collect(Collectors.toList());
        if (capitalizedRolesRequiredList.contains(rolePublic))
            return true;
        final boolean isAny = capitalizedRolesRequiredList.contains(roleAny);
        return user.getGroups().stream()
                .filter(group -> group.startsWith(rolePrefix))
                .map(group -> group.substring(rolePrefix.length()))
                .anyMatch(role -> isAny || capitalizedRolesRequiredList.contains(role) || role.equals(roleAdmin));
    }

    private TokenUser getUserFromToken() throws Exception {
        final HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final Cookie tokenCookie = Arrays.stream(servletRequest.getCookies()).filter(c -> c.getName().equals(tokenName)).findFirst().orElseThrow();
        final String token = new String(Base64.getDecoder().decode(tokenCookie.getValue()));
        final byte[] secretBytes = secretKey.getBytes();
        final Claims claims = Jwts.parser().setSigningKey(secretBytes).parseClaimsJws(token).getBody();
        if (claims.getExpiration().compareTo(new Date()) < 0)
            throw new Exception("Token expired!");
        return new ObjectMapper().readValue(claims.getSubject(), TokenUser.class);
    }
}