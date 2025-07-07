package retoPragma.Microusuario.domain.UseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.domain.model.User;
import retoPragma.Microusuario.domain.spi.ISecurityServicePort;
import retoPragma.Microusuario.domain.spi.IRestaurantPlazoletaFeignPort;
import retoPragma.Microusuario.domain.spi.IUserPersistencePort;
import retoPragma.Microusuario.domain.util.exception.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    IUserPersistencePort userPersistencePort;

    @Mock
    ISecurityServicePort securityServicePort;

    @Mock
    IRestaurantPlazoletaFeignPort smallSquareServicePort;

    @InjectMocks
    UserUseCase userUseCase;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John");
        user.setLastName("Gomez");
        user.setIdentificationNumber(123456L);
        user.setNumberPhone("+573001234567");
        user.setDateOfBirth(LocalDate.of(2000, 1, 1));
        user.setEmail("john.gomez@example.com");
        user.setClave("secret");
        user.setIdRestaurant(10L);
    }

    @Test
    void saveUser_firstAdmin_shouldCallValidateAndSaveUser() {
        user.setRol(RolesPlazoleta.ADMINISTRADOR);
        when(userPersistencePort.findByRol(RolesPlazoleta.ADMINISTRADOR)).thenReturn(Optional.empty());
        when(securityServicePort.encodePassword(anyString())).thenReturn("encoded");

        userUseCase.saveUser(user);

        verify(userPersistencePort).saveUser(any(User.class));
    }

    @Test
    void saveUser_withAdminRole_shouldCallValidateAndSaveUser() {
        user.setRol(RolesPlazoleta.ADMINISTRADOR);
        when(userPersistencePort.findByRol(RolesPlazoleta.ADMINISTRADOR)).thenReturn(Optional.of(user));
        when(securityServicePort.getAuthenticatedAuthority()).thenReturn("ROLE_ADMINISTRADOR");
        when(securityServicePort.encodePassword(anyString())).thenReturn("encoded");

        userUseCase.saveUser(user);

        verify(userPersistencePort).saveUser(any(User.class));
    }

    @Test
    void saveUser_ownerCreatesEmployee_shouldSucceed() {
        user.setRol(RolesPlazoleta.EMPLEADO);
        when(securityServicePort.getAuthenticatedAuthority()).thenReturn("ROLE_PROPIETARIO");
        when(securityServicePort.getAuthenticatedUsername()).thenReturn("owner@example.com");
        when(userPersistencePort.findByEmail("owner@example.com")).thenReturn(
                new User(5L, "Owner", "Last", 123L, "+573000000000", LocalDate.of(1990, 1, 1), "owner@example.com", "pw", RolesPlazoleta.PROPIETARIO, 10L)
        );
        when(smallSquareServicePort.obtainRestaurantId(user.getIdRestaurant())).thenReturn(10L);
        when(securityServicePort.encodePassword(anyString())).thenReturn("encoded");

        userUseCase.saveUser(user);

        verify(userPersistencePort).saveUser(any(User.class));
    }

    @Test
    void saveUser_ownerCreatesAdmin_shouldThrowException() {
        user.setRol(RolesPlazoleta.ADMINISTRADOR);
        when(userPersistencePort.findByRol(RolesPlazoleta.ADMINISTRADOR)).thenReturn(Optional.of(new User()));
        when(securityServicePort.getAuthenticatedAuthority()).thenReturn("ROLE_PROPIETARIO");

        assertThrows(NoPermissionCreateException.class, () -> userUseCase.saveUser(user));
    }

    @Test
    void findRolById_shouldReturnRole() {
        when(userPersistencePort.findRolById(1L)).thenReturn("ADMINISTRADOR");
        String role = userUseCase.findRolById(1L);
        assertEquals("ADMINISTRADOR", role);
    }

    @Test
    void findUserByEmail_shouldReturnUser() {
        when(userPersistencePort.findByEmail("john.doe@example.com")).thenReturn(user);
        User result = userUseCase.findUserByEmail("john.doe@example.com");
        assertEquals(user, result);
    }

    @Test
    void saveRegister_validClient_shouldSucceed() {
        user.setRol(RolesPlazoleta.CLIENTE);
        when(securityServicePort.encodePassword(anyString())).thenReturn("encoded");

        userUseCase.saveRegister(user);

        verify(userPersistencePort).saveUser(any(User.class));
    }

    @Test
    void saveRegister_nonClient_shouldThrowException() {
        user.setRol(RolesPlazoleta.EMPLEADO);

        assertThrows(NoPermissionCreateException.class, () -> userUseCase.saveRegister(user));
    }

    @Test
    void createEmployeeForOwner_valid_shouldSucceed() {
        user.setRol(RolesPlazoleta.EMPLEADO);
        when(securityServicePort.getAuthenticatedAuthority()).thenReturn("ROLE_PROPIETARIO");
        when(securityServicePort.getAuthenticatedUsername()).thenReturn("owner@example.com");
        when(userPersistencePort.findByEmail("owner@example.com")).thenReturn(
                new User(5L, "Owner", "Last", 123L, "+573000000000", LocalDate.of(1990, 1, 1), "owner@example.com", "pw", RolesPlazoleta.PROPIETARIO, 10L)
        );
        when(smallSquareServicePort.obtainRestaurantId(user.getIdRestaurant())).thenReturn(10L);
        when(securityServicePort.encodePassword(anyString())).thenReturn("encoded");

        userUseCase.createEmployeeForOwner(user, 5L);

        verify(userPersistencePort).saveUser(any(User.class));
    }

    @Test
    void createEmployeeForOwner_invalidRestaurant_shouldThrowException() {
        user.setRol(RolesPlazoleta.EMPLEADO);
        when(securityServicePort.getAuthenticatedAuthority()).thenReturn("ROLE_PROPIETARIO");
        when(smallSquareServicePort.obtainRestaurantId(user.getIdRestaurant())).thenReturn(null);

        assertThrows(NoPermissionCreateException.class, () -> userUseCase.createEmployeeForOwner(user, 1L));
    }

    @Test
    void saveUser_ownerIsNull_shouldThrowAuthNoOwnerException() {
        user.setRol(RolesPlazoleta.EMPLEADO);
        when(securityServicePort.getAuthenticatedAuthority()).thenReturn("ROLE_PROPIETARIO");
        when(securityServicePort.getAuthenticatedUsername()).thenReturn("owner@example.com");
        when(userPersistencePort.findByEmail("owner@example.com")).thenReturn(null);

        assertThrows(AuthNoOwnerException.class, () -> userUseCase.saveUser(user));
    }

    @Test
    void createEmployeeForOwner_ownerIdMismatch_shouldThrowAuthNoOwnerException() {
        user.setRol(RolesPlazoleta.EMPLEADO);
        when(securityServicePort.getAuthenticatedAuthority()).thenReturn("ROLE_PROPIETARIO");
        when(securityServicePort.getAuthenticatedUsername()).thenReturn("owner@example.com");
        when(userPersistencePort.findByEmail("owner@example.com")).thenReturn(
                new User(99L, "OtherOwner", "Mismatch", 123L, "+573000000000", LocalDate.of(1980, 1, 1), "owner@example.com", "pw", RolesPlazoleta.PROPIETARIO, 10L)
        );
        when(smallSquareServicePort.obtainRestaurantId(user.getIdRestaurant())).thenReturn(10L);

        assertThrows(AuthNoOwnerException.class, () -> userUseCase.createEmployeeForOwner(user, 1L));
    }

    @Test
    void createEmployeeForOwner_notOwnerAuthority_shouldThrowUserNoOwnerException() {
        when(securityServicePort.getAuthenticatedAuthority()).thenReturn("ROLE_ADMINISTRADOR");

        assertThrows(UserNoOwnerException.class, () -> userUseCase.createEmployeeForOwner(user, 1L));
    }

    @Test
    void createEmployeeForOwner_ownerNull_shouldThrowAuthNoOwnerException() {
        when(securityServicePort.getAuthenticatedAuthority()).thenReturn("ROLE_PROPIETARIO");
        when(securityServicePort.getAuthenticatedUsername()).thenReturn("owner@example.com");
        when(userPersistencePort.findByEmail("owner@example.com")).thenReturn(null);
        when(smallSquareServicePort.obtainRestaurantId(user.getIdRestaurant())).thenReturn(10L);

        assertThrows(AuthNoOwnerException.class, () -> userUseCase.createEmployeeForOwner(user, 1L));
    }

    @Test
    void validateEmployee_nullName_shouldThrowFullNameException() {
        user.setRol(RolesPlazoleta.EMPLEADO);
        user.setName(null);
        user.setLastName("Doe");
        when(securityServicePort.getAuthenticatedAuthority()).thenReturn("ROLE_PROPIETARIO");
        when(securityServicePort.getAuthenticatedUsername()).thenReturn("owner@example.com");
        when(userPersistencePort.findByEmail("owner@example.com")).thenReturn(
                new User(1L, "Owner", "Test", 123L, "+573000000000", LocalDate.of(2000, 1, 1), "owner@example.com", "pw", RolesPlazoleta.PROPIETARIO, 10L)
        );
        when(smallSquareServicePort.obtainRestaurantId(user.getIdRestaurant())).thenReturn(10L);

        assertThrows(FullNameException.class, () -> userUseCase.createEmployeeForOwner(user, 1L));
    }

    @Test
    void saveUser_invalidEmail_shouldThrowCorreoInvadedException() {
        user.setRol(RolesPlazoleta.ADMINISTRADOR);
        user.setEmail("invalid-email");
        when(userPersistencePort.findByRol(RolesPlazoleta.ADMINISTRADOR)).thenReturn(Optional.empty());

        assertThrows(CorreoInvadedException.class, () -> userUseCase.saveUser(user));
    }

    @Test
    void saveUser_invalidPhone_shouldThrowPhoneInvalidException() {
        user.setRol(RolesPlazoleta.ADMINISTRADOR);
        user.setNumberPhone("3001234567");
        when(userPersistencePort.findByRol(RolesPlazoleta.ADMINISTRADOR)).thenReturn(Optional.empty());

        assertThrows(PhoneInvalidException.class, () -> userUseCase.saveUser(user));
    }

    @Test
    void saveUser_invalidIdentification_shouldThrowDocumentException() {
        user.setRol(RolesPlazoleta.ADMINISTRADOR);
        user.setIdentificationNumber(0L);
        when(userPersistencePort.findByRol(RolesPlazoleta.ADMINISTRADOR)).thenReturn(Optional.empty());

        assertThrows(DocumentException.class, () -> userUseCase.saveUser(user));
    }

    @Test
    void saveUser_underage_shouldThrowAgeException() {
        user.setRol(RolesPlazoleta.ADMINISTRADOR);
        user.setDateOfBirth(LocalDate.now().minusYears(10));
        when(userPersistencePort.findByRol(RolesPlazoleta.ADMINISTRADOR)).thenReturn(Optional.empty());

        assertThrows(AgeException.class, () -> userUseCase.saveUser(user));
    }
}
