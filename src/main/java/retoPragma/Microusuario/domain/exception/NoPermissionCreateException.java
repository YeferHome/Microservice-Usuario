package retoPragma.Microusuario.domain.exception;

public class NoPermissionCreateException extends RuntimeException {
    public NoPermissionCreateException() {
        super("No tienes permisos para crear este tipo de usuario.");
    }
}
