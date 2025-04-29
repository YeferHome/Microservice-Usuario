package retoPragma.Microusuario.domain.exception;

public class NoAuthUserException extends RuntimeException {
    public NoAuthUserException() {
        super("No estás autenticado, no se puede crear un usuario.");
    }
}