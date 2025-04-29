package retoPragma.Microusuario.domain.exception;

public class AgeException extends RuntimeException {
    public AgeException() {
        super("El usuario debe ser mayor de 18 años.");
    }
}
