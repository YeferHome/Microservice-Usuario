package retoPragma.Microusuario.domain.util.exception;

public class PhoneInvalidException extends RuntimeException {
    public PhoneInvalidException() {
        super("El número de teléfono no es válido");
    }
}
