package retoPragma.Microusuario.domain.util.exception;

public class PasswordErrorException extends RuntimeException {
    public PasswordErrorException() {
        super("Contraseña invalida");
    }
}
