package retoPragma.Microusuario.domain.exception;

public class PhoneInvalidException extends RuntimeException {
    public PhoneInvalidException() {
        super("El número de teléfono no es válido");
    }
}
