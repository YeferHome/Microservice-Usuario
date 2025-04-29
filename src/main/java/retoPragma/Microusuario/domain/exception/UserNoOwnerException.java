package retoPragma.Microusuario.domain.exception;

public class UserNoOwnerException extends RuntimeException {
    public UserNoOwnerException() {
        super("Solo un propietario puede crear empleados");
    }
}
