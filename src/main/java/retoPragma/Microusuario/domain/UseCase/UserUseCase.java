package retoPragma.Microusuario.domain.UseCase;

import retoPragma.Microusuario.domain.api.IUserServicePort;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.domain.model.User;
import retoPragma.Microusuario.domain.spi.ISmallSquareServicePort;
import retoPragma.Microusuario.domain.spi.ISecurityServicePort;
import retoPragma.Microusuario.domain.spi.IUserPersistencePort;
import retoPragma.Microusuario.domain.util.validation.UserValidationUtil;
import retoPragma.Microusuario.domain.util.exception.AuthNoOwnerException;
import retoPragma.Microusuario.domain.util.exception.FullNameException;
import retoPragma.Microusuario.domain.util.exception.NoPermissionCreateException;
import retoPragma.Microusuario.domain.util.exception.UserNoOwnerException;

import static retoPragma.Microusuario.domain.model.RolesPlazoleta.ADMINISTRADOR;
import static retoPragma.Microusuario.domain.model.RolesPlazoleta.EMPLEADO;
import static retoPragma.Microusuario.domain.util.constants.RolesConstantes.ROLE_ADMINISTRADOR;
import static retoPragma.Microusuario.domain.util.constants.RolesConstantes.ROLE_PROPIETARIO;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final ISecurityServicePort securityServicePort;
    private final ISmallSquareServicePort smallSquareFeignClient;
    private final UserValidationUtil userValidationUtil;

    public UserUseCase(IUserPersistencePort userPersistencePort, ISecurityServicePort securityServicePort, ISmallSquareServicePort smallSquareFeignClient) {
        this.userPersistencePort = userPersistencePort;
        this.securityServicePort = securityServicePort;
        this.smallSquareFeignClient = smallSquareFeignClient;
        this.userValidationUtil = new UserValidationUtil(securityServicePort, userPersistencePort, smallSquareFeignClient);
    }

    @Override
    public void saveUser(User user) {
        if (user.getRol() == ADMINISTRADOR && userValidationUtil.isFirstAdmin()) {
            userValidationUtil.validateAndSaveUser(user);
            return;
        }

        String authority = securityServicePort.getAuthenticatedAuthority();

        switch (authority) {
            case ROLE_ADMINISTRADOR:
                userValidationUtil.validateAndSaveUser(user);
                break;
            case ROLE_PROPIETARIO:
                if (user.getRol() == EMPLEADO) {
                    String emailAuthenticated = securityServicePort.getAuthenticatedUsername();
                    User owner = userPersistencePort.findByEmail(emailAuthenticated);
                    if (owner == null) {
                        throw new AuthNoOwnerException();
                    }
                    Long idOwner = owner.getId();
                    createEmployeeForOwner(user, idOwner);
                } else {
                    throw new NoPermissionCreateException();
                }
                break;
            default:
                throw new NoPermissionCreateException();
        }
    }


    @Override
    public String findRolById(long id) {
        return userPersistencePort.findRolById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userPersistencePort.findByEmail(email);
    }

    @Override
    public void saveRegister(User user) {
        if (user.getRol() != RolesPlazoleta.CLIENTE) {
            throw new NoPermissionCreateException();
        }
        userValidationUtil.validateAndSaveUser(user);
    }

    public void createEmployeeForOwner(User employee, Long idOwner) {

        String authority = securityServicePort.getAuthenticatedAuthority();
        if (!ROLE_PROPIETARIO.equals(authority)) {
            throw new UserNoOwnerException();
        }
        if (smallSquareFeignClient.obtainRestaurantId(employee.getIdRestaurant()) == null) {
            throw new NoPermissionCreateException();
        }
        String emailAuthentic = securityServicePort.getAuthenticatedUsername();
        User owner = userPersistencePort.findByEmail(emailAuthentic);

        if (owner == null || !owner.getId().equals(idOwner)) {
            throw new AuthNoOwnerException();
        }

        employee.setRol(EMPLEADO);
        validateEmployee(employee);
        userValidationUtil.saveUserWithEncryptedKey(employee);
    }

    private void validateEmployee(User user) {
        if (user.getName() == null || user.getLastName() == null) {
            throw new FullNameException();
        }
        userValidationUtil.generalValidation(user);
    }
}