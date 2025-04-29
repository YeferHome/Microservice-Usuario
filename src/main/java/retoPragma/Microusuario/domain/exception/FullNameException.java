package retoPragma.Microusuario.domain.exception;

public class FullNameException extends RuntimeException {
    public FullNameException() {
        super("Nombre y Apellido obligatorios.");
    }
}
