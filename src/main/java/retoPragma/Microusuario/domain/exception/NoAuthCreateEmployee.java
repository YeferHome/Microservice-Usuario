package retoPragma.Microusuario.domain.exception;

public class NoAuthCreateEmployee extends RuntimeException {
    public NoAuthCreateEmployee() {
        super("Debes estar autenticado para crear empleados.");
    }
}
