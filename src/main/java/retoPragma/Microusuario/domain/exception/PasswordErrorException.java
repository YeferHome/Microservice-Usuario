package retoPragma.Microusuario.domain.exception;

public class PasswordErrorException extends RuntimeException {
    public PasswordErrorException() {
        super("Contraseña invalida");
    }
}
