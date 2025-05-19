package retoPragma.Microusuario.application.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import retoPragma.Microusuario.application.dto.UserAppRequestDto;
import retoPragma.Microusuario.application.mapper.IUserAppRequestMapper;
import retoPragma.Microusuario.domain.api.IUserServicePort;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.domain.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserAppHandlerTest {

    @Mock
    private IUserServicePort usuarioServicePort;

    @Mock
    private IUserAppRequestMapper usuarioAppRequestMapper;

    @InjectMocks
    private UserAppHandler userAppHandler;

    private UserAppRequestDto requestDto;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        requestDto = new UserAppRequestDto();
        requestDto.setName("Juan");
        requestDto.setLastname("Pérez");
        requestDto.setEmail("juan@example.com");
        requestDto.setNumberPhone("+573001234567");
        requestDto.setIdentificationNumber(12345678L);
        requestDto.setDateOfBirth(LocalDate.of(1990, 1, 1));
        requestDto.setClave("clave123");
        requestDto.setRol(RolesPlazoleta.CLIENTE);

        user = new User();
        user.setId(1L);
        user.setName("Juan");
        user.setLastName("Pérez");
        user.setEmail("juan@example.com");
        user.setNumberPhone("+573001234567");
        user.setIdentificationNumber(12345678L);
        user.setDateOfBirth(LocalDate.of(1990, 1, 1));
        user.setClave("clave123");
        user.setRol(RolesPlazoleta.CLIENTE);
        user.setIdRestaurant(null);
    }

    @Test
    void saveUsuarioInUsuarioApp() {
        when(usuarioAppRequestMapper.toUser(requestDto)).thenReturn(user);

        userAppHandler.saveUserInUserApp(requestDto);

        verify(usuarioAppRequestMapper).toUser(requestDto);
        verify(usuarioServicePort).saveUser(user);
    }

    @Test
    void savePropietarioInUsuarioApp() {
        when(usuarioAppRequestMapper.toUser(requestDto)).thenReturn(user);

        userAppHandler.saveOwnerInUserApp(requestDto);

        verify(usuarioAppRequestMapper).toUser(requestDto);
        verify(usuarioServicePort).saveUser(user);
    }

    @Test
    void saveEmpleadoInUsuarioApp() {
        when(usuarioAppRequestMapper.toUser(requestDto)).thenReturn(user);

        userAppHandler.saveEmployeeInUserApp(requestDto);

        verify(usuarioAppRequestMapper).toUser(requestDto);
        verify(usuarioServicePort).saveUser(user);
    }

    @Test
    void findRolById() {
        Long userId = 1L;
        when(usuarioServicePort.findRolById(userId)).thenReturn("CLIENTE");

        String rol = userAppHandler.findRolById(userId);

        assertEquals("CLIENTE", rol);
        verify(usuarioServicePort).findRolById(userId);
    }
}
