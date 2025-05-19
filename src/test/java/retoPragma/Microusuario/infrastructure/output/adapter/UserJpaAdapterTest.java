package retoPragma.Microusuario.infrastructure.output.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.domain.model.User;
import retoPragma.Microusuario.domain.util.exception.DocumentNoExistException;
import retoPragma.Microusuario.infrastructure.output.entity.UserEntity;
import retoPragma.Microusuario.infrastructure.output.mapper.IUserEntityMapper;
import retoPragma.Microusuario.infrastructure.output.repository.IUserRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserJpaAdapterTest {

    private IUserRepository usuarioRepository;
    private IUserEntityMapper usuarioEntityMapper;
    private UserJpaAdapter usuarioJpaAdapter;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(IUserRepository.class);
        usuarioEntityMapper = mock(IUserEntityMapper.class);
        usuarioJpaAdapter = new UserJpaAdapter(usuarioRepository, usuarioEntityMapper);

        user = new User();
        user.setId(1L);
        user.setName("Juan");
        user.setLastName("Pérez");
        user.setEmail("juan@example.com");
        user.setClave("clave123");
        user.setNumberPhone("+573001234567");
        user.setIdentificationNumber(12345678L);
        user.setDateOfBirth(LocalDate.of(1990, 1, 1));
        user.setRol(RolesPlazoleta.CLIENTE);

        userEntity = new UserEntity();
    }

    @Test
    void saveUsuario_ShouldSaveWhenDocumentoDoesNotExist() {
        when(usuarioRepository.findByIdentificationNumber(user.getIdentificationNumber())).thenReturn(Optional.empty());
        when(usuarioEntityMapper.toUsuarioEntity(user)).thenReturn(userEntity);

        usuarioJpaAdapter.saveUser(user);

        verify(usuarioRepository).save(userEntity);
    }

    @Test
    void saveUsuario_ShouldThrowException_WhenDocumentoExists() {
        when(usuarioRepository.findByIdentificationNumber(user.getIdentificationNumber()))
                .thenReturn(Optional.of(userEntity));

        assertThrows(DocumentNoExistException.class, () -> usuarioJpaAdapter.saveUser(user));
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void findRolById_ShouldReturnRol() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(usuarioEntityMapper.toUser(userEntity)).thenReturn(user);

        String rol = usuarioJpaAdapter.findRolById(1L);

        assertEquals("CLIENTE", rol);
    }

    @Test
    void findRolById_ShouldReturnNull_WhenUserNotFound() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        String rol = usuarioJpaAdapter.findRolById(1L);

        assertNull(rol);
    }

    @Test
    void findByCorreo_ShouldReturnUsuario() {
        when(usuarioRepository.findByEmail("juan@example.com")).thenReturn(Optional.of(userEntity));
        when(usuarioEntityMapper.toUser(userEntity)).thenReturn(user);

        User result = usuarioJpaAdapter.findByEmail("juan@example.com");

        assertNotNull(result);
        assertEquals("juan@example.com", result.getEmail());
    }

    @Test
    void findByCorreo_ShouldReturnNull_WhenNotFound() {
        when(usuarioRepository.findByEmail("juan@example.com")).thenReturn(Optional.empty());

        User result = usuarioJpaAdapter.findByEmail("juan@example.com");

        assertNull(result);
    }

    @Test
    void findByRol_ShouldReturnUsuarioOptional() {
        when(usuarioRepository.findByRol(RolesPlazoleta.CLIENTE)).thenReturn(Optional.of(userEntity));
        when(usuarioEntityMapper.toUser(userEntity)).thenReturn(user);

        Optional<User> result = usuarioJpaAdapter.findByRol(RolesPlazoleta.CLIENTE);

        assertTrue(result.isPresent());
        assertEquals("juan@example.com", result.get().getEmail());
    }

    @Test
    void findByRol_ShouldReturnEmpty_WhenNotFound() {
        when(usuarioRepository.findByRol(RolesPlazoleta.CLIENTE)).thenReturn(Optional.empty());

        Optional<User> result = usuarioJpaAdapter.findByRol(RolesPlazoleta.CLIENTE);

        assertTrue(result.isEmpty());
    }
}
