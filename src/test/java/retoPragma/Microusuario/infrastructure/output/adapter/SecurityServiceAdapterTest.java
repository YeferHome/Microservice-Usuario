package retoPragma.Microusuario.infrastructure.output.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import retoPragma.Microusuario.domain.util.exception.NoAuthUserException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SecurityServiceAdapterTest {

    private SecurityServiceAdapter securityServiceAdapter;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        securityServiceAdapter = new SecurityServiceAdapter(passwordEncoder);
    }

    @Test
    void encodePassword_ShouldReturnEncodedPassword() {
        String rawPassword = "plain123";
        String encoded = securityServiceAdapter.encodePassword(rawPassword);

        assertNotNull(encoded);
        assertTrue(passwordEncoder.matches(rawPassword, encoded));
    }

    @Test
    void getAuthenticatedUsername_ShouldReturnUsername() {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                "usuario@example.com",
                "password",
                List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"))
        );

        try (MockedStatic<SecurityContextHolder> mockedHolder = org.mockito.Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext context = org.mockito.Mockito.mock(SecurityContext.class);
            mockedHolder.when(SecurityContextHolder::getContext).thenReturn(context);
            org.mockito.Mockito.when(context.getAuthentication()).thenReturn(auth);

            String username = securityServiceAdapter.getAuthenticatedUsername();
            assertEquals("usuario@example.com", username);
        }
    }

    @Test
    void getAuthenticatedAuthority_ShouldReturnRole() {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                "usuario@example.com",
                "password",
                List.of(new SimpleGrantedAuthority("ROLE_PROPIETARIO"))
        );

        try (MockedStatic<SecurityContextHolder> mockedHolder = org.mockito.Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext context = org.mockito.Mockito.mock(SecurityContext.class);
            mockedHolder.when(SecurityContextHolder::getContext).thenReturn(context);
            org.mockito.Mockito.when(context.getAuthentication()).thenReturn(auth);

            String role = securityServiceAdapter.getAuthenticatedAuthority();
            assertEquals("ROLE_PROPIETARIO", role);
        }
    }

    @Test
    void getAuthenticatedUsername_ShouldThrowException_WhenNoAuth() {
        try (MockedStatic<SecurityContextHolder> mockedHolder = org.mockito.Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext context = org.mockito.Mockito.mock(SecurityContext.class);
            mockedHolder.when(SecurityContextHolder::getContext).thenReturn(context);
            org.mockito.Mockito.when(context.getAuthentication()).thenReturn(null);

            assertThrows(NoAuthUserException.class, () -> securityServiceAdapter.getAuthenticatedUsername());
        }
    }

    @Test
    void getAuthenticatedAuthority_ShouldThrowException_WhenEmptyAuthorities() {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                "usuario@example.com",
                "password",
                Collections.emptyList()
        );

        try (MockedStatic<SecurityContextHolder> mockedHolder = org.mockito.Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext context = org.mockito.Mockito.mock(SecurityContext.class);
            mockedHolder.when(SecurityContextHolder::getContext).thenReturn(context);
            org.mockito.Mockito.when(context.getAuthentication()).thenReturn(auth);

            assertThrows(NoAuthUserException.class, () -> securityServiceAdapter.getAuthenticatedAuthority());
        }
    }
}
