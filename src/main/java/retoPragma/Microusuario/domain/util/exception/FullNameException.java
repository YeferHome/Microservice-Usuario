package retoPragma.Microusuario.domain.util.exception;

public class FullNameException extends RuntimeException {
    public FullNameException() {
        super("Nombre y Apellido obligatorios.");
    }
}
