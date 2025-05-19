package retoPragma.Microusuario.domain.util.exception;

public class UserNoOwnerException extends RuntimeException {
    public UserNoOwnerException() {
        super("Solo un propietario puede crear empleados");
    }
}
