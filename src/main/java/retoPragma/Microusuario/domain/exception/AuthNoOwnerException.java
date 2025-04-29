package retoPragma.Microusuario.domain.exception;

public class AuthNoOwnerException extends RuntimeException {
    public AuthNoOwnerException() {
        super("El usuario autenticado no coincide con el propietario indicado.");
    }
}
