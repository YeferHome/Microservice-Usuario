package retoPragma.Microusuario.domain.UseCase;

import retoPragma.Microusuario.domain.model.User;
import retoPragma.Microusuario.domain.util.validation.UserValidationUtil;
import retoPragma.Microusuario.domain.util.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserValidationUtilTest {

    private UserValidationUtil userValidationUtil;

    @BeforeEach
    void setUp() {

        userValidationUtil = new UserValidationUtil(null, null, null);
    }

    @Test
    void generalValidation_CorrectData_NoException() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setNumberPhone("+573001234567");
        user.setIdentificationNumber(12345678L);
        user.setDateOfBirth(LocalDate.now().minusYears(20));

        assertDoesNotThrow(() -> userValidationUtil.generalValidation(user));
    }

    @Test
    void generalValidation_InvalidEmail_ThrowsEmailInvadedException() {
        User user = new User();
        user.setEmail("email_invalid");
        user.setNumberPhone("+573001234567");
        user.setIdentificationNumber(12345678L);
        user.setDateOfBirth(LocalDate.now().minusYears(20));

        assertThrows(CorreoInvadedException.class, () -> userValidationUtil.generalValidation(user));
    }

    @Test
    void generalValidation_InvalidPhone_ThrowsPhoneInvalidException() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setNumberPhone("12345");
        user.setIdentificationNumber(12345678L);
        user.setDateOfBirth(LocalDate.now().minusYears(20));

        assertThrows(PhoneInvalidException.class, () -> userValidationUtil.generalValidation(user));
    }

    @Test
    void generalValidation_InvalidDocument_ThrowsDocumentException() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setNumberPhone("+573001234567");
        user.setIdentificationNumber(0L);
        user.setDateOfBirth(LocalDate.now().minusYears(20));

        assertThrows(DocumentException.class, () -> userValidationUtil.generalValidation(user));
    }

    @Test
    void generalValidation_InvalidAge_ThrowsAgeException() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setNumberPhone("+573001234567");
        user.setIdentificationNumber(12345678L);
        user.setDateOfBirth(LocalDate.now().minusYears(10));

        assertThrows(AgeException.class, () -> userValidationUtil.generalValidation(user));
    }
}
