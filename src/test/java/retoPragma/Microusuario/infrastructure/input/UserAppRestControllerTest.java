package retoPragma.Microusuario.infrastructure.input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retoPragma.Microusuario.application.dto.UserAppRequestDto;
import retoPragma.Microusuario.application.handler.IUserAppHandler;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserAppRestControllerTest {

    private IUserAppHandler usuarioAppHandler;
    private UserAppRestController controller;

    @BeforeEach
    void setUp() {
        usuarioAppHandler = mock(IUserAppHandler.class);
        controller = new UserAppRestController(usuarioAppHandler);
    }

    @Test
    void saveUsuarioInUsuarioApp() {
        UserAppRequestDto dto = new UserAppRequestDto();

        ResponseEntity<Void> response = controller.saveUserInUserApp(dto);

        verify(usuarioAppHandler, times(1)).saveUserInUserApp(dto);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void savePropietarioInUsuarioApp() {
        UserAppRequestDto dto = new UserAppRequestDto();

        ResponseEntity<Void> response = controller.saveOwnerInUserApp(dto);

        verify(usuarioAppHandler, times(1)).saveOwnerInUserApp(dto);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void saveEmpleadoInUsuarioApp() {
        UserAppRequestDto dto = new UserAppRequestDto();

        ResponseEntity<Void> response = controller.saveEmployeeInUserApp(dto);

        verify(usuarioAppHandler, times(1)).saveEmployeeInUserApp(dto);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void obtenerRolUsuario() {
        Long userId = 1L;
        when(usuarioAppHandler.findRolById(userId)).thenReturn("ADMIN");

        String rol = controller.obtainRolUser(userId);

        verify(usuarioAppHandler, times(1)).findRolById(userId);
        assertEquals("ADMIN", rol);
    }
}
