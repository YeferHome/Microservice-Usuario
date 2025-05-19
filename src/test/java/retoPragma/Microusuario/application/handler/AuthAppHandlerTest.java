package retoPragma.Microusuario.application.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import retoPragma.Microusuario.application.dto.LoginRequestDto;
import retoPragma.Microusuario.application.dto.LoginResponseDto;
import retoPragma.Microusuario.application.dto.RegisterRequestDto;
import retoPragma.Microusuario.application.mapper.IUserAppRequestMapper;
import retoPragma.Microusuario.domain.api.IUserServicePort;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.domain.model.User;
import retoPragma.Microusuario.domain.util.exception.PasswordErrorException;
import retoPragma.Microusuario.infrastructure.configuracion.jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthAppHandlerTest {

    @Mock
    private IUserServicePort usuarioServicePort;

    @Mock
    private IUserAppRequestMapper usuarioAppRequestMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthAppHandler authAppHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ValidCredentials_ReturnsJwtToken() {
        // Arrange
        LoginRequestDto loginRequest = new LoginRequestDto();
        loginRequest.setCorreo("juan@example.com");
        loginRequest.setClave("plainPassword");

        User user = new User();
        user.setEmail("juan@example.com");
        user.setClave("encodedPassword");

        when(usuarioServicePort.findUserByEmail("juan@example.com")).thenReturn(user);
        when(passwordEncoder.matches("plainPassword", "encodedPassword")).thenReturn(true);
        when(jwtService.generate(user)).thenReturn("mocked-jwt");

        // Act
        LoginResponseDto response = authAppHandler.login(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals("mocked-jwt", response.getToken());
    }

    @Test
    void login_InvalidPassword_ThrowsPasswordErrorException() {
        // Arrange
        LoginRequestDto loginRequest = new LoginRequestDto();
        loginRequest.setCorreo("juan@example.com");
        loginRequest.setClave("wrongPassword");

        User user = new User();
        user.setEmail("juan@example.com");
        user.setClave("correctEncodedPassword");

        when(usuarioServicePort.findUserByEmail("juan@example.com")).thenReturn(user);
        when(passwordEncoder.matches("wrongPassword", "correctEncodedPassword")).thenReturn(false);

        // Act & Assert
        assertThrows(PasswordErrorException.class, () -> authAppHandler.login(loginRequest));
    }

    @Test
    void register_ValidData_SavesUsuario() {
        // Arrange
        RegisterRequestDto registerRequest = new RegisterRequestDto();
        registerRequest.setNombre("Juan");
        registerRequest.setApellido("Pérez");
        registerRequest.setCorreo("juan@example.com");
        registerRequest.setClave("clave123");
        registerRequest.setCelular("+573001234567");
        registerRequest.setDocumentoDeIdentidad(12345678L);
        registerRequest.setFechaNacimiento(LocalDate.parse("1990-01-01"));
        registerRequest.setRol(RolesPlazoleta.CLIENTE);

        User user = new User();
        when(usuarioAppRequestMapper.toRegister(registerRequest)).thenReturn(user);

        // Act
        authAppHandler.register(registerRequest);

        // Assert
        verify(usuarioAppRequestMapper).toRegister(registerRequest);
        verify(usuarioServicePort).saveRegister(user);
    }
}
