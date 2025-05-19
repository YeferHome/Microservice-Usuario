package retoPragma.Microusuario.infrastructure.output.adapter;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import retoPragma.Microusuario.domain.util.exception.NoAuthUserException;
import retoPragma.Microusuario.domain.spi.ISecurityServicePort;

@Component
public class SecurityServiceAdapter implements ISecurityServicePort {

    private final BCryptPasswordEncoder passwordEncoder;

    public SecurityServiceAdapter(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public String getAuthenticatedUsername() {
        Authentication auth = getAuth();
        return auth.getName();
    }

    @Override
    public String getAuthenticatedAuthority() {
        Authentication auth = getAuth();
        return auth.getAuthorities().iterator().next().getAuthority();
    }

    private Authentication getAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getAuthorities().isEmpty()) {
            throw new NoAuthUserException(); }

        return auth;
    }
}