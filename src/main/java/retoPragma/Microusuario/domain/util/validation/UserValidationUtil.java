package retoPragma.Microusuario.domain.util.validation;

import retoPragma.Microusuario.domain.spi.ISmallSquareServicePort;
import retoPragma.Microusuario.domain.util.exception.AgeException;
import retoPragma.Microusuario.domain.util.exception.CorreoInvadedException;
import retoPragma.Microusuario.domain.util.exception.DocumentException;
import retoPragma.Microusuario.domain.util.exception.PhoneInvalidException;
import retoPragma.Microusuario.domain.model.User;
import retoPragma.Microusuario.domain.spi.ISecurityServicePort;
import retoPragma.Microusuario.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.time.Period;

import static retoPragma.Microusuario.domain.util.constants.RegexConstants.CELULAR_REGEX;
import static retoPragma.Microusuario.domain.util.constants.RegexConstants.CORREO_REGEX;
import static retoPragma.Microusuario.domain.util.constants.UserConstants.DOCUMENTO_POSITIVO;
import static retoPragma.Microusuario.domain.util.constants.UserConstants.Edad_MINIMA;
import static retoPragma.Microusuario.domain.model.RolesPlazoleta.ADMINISTRADOR;

public class UserValidationUtil {

    private final ISecurityServicePort securityServicePort;
    private final IUserPersistencePort userPersistencePort;
    private final ISmallSquareServicePort smallSquareFeignClient;


    public UserValidationUtil(ISecurityServicePort securityServicePort, IUserPersistencePort userPersistencePort, ISmallSquareServicePort smallSquareFeignClient) {
        this.securityServicePort = securityServicePort;
        this.userPersistencePort = userPersistencePort;
        this.smallSquareFeignClient = smallSquareFeignClient;
    }

    public void validateAndSaveUser(User user) {
        generalValidation(user);
        saveUserWithEncryptedKey(user);
    }

    public void saveUserWithEncryptedKey(User user) {
        user.setClave(securityServicePort.encodePassword(user.getClave()));
        userPersistencePort.saveUser(user);
    }

    public boolean isFirstAdmin() {
        return userPersistencePort.findByRol(ADMINISTRADOR).isEmpty();
    }


    public void generalValidation(User user) {
        if (!emailValidate(user.getEmail())) {
            throw new CorreoInvadedException();
        }
        if (!phoneValidate(user.getNumberPhone())) {
            throw new PhoneInvalidException();
        }
        if (user.getIdentificationNumber() == null || user.getIdentificationNumber() <= DOCUMENTO_POSITIVO) {
            throw new DocumentException();
        }
        if (!olderAge(user.getDateOfBirth())) {
            throw new AgeException();
        }
    }
    public boolean emailValidate(String email) {
        return email != null && email.matches(CORREO_REGEX);
    }

    public boolean phoneValidate(String numberPhone) {
        return numberPhone != null && numberPhone.matches(CELULAR_REGEX);
    }

    public boolean olderAge(LocalDate dateOfBirth) {
        return dateOfBirth != null && Period.between(dateOfBirth, LocalDate.now()).getYears() >= Edad_MINIMA;
    }
}

