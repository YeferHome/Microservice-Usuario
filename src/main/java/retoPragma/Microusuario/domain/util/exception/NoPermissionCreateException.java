package retoPragma.Microusuario.domain.util.exception;

public class NoPermissionCreateException extends RuntimeException {
    public NoPermissionCreateException() {
        super("No tienes permisos para crear este tipo de usuario.");
    }
}
