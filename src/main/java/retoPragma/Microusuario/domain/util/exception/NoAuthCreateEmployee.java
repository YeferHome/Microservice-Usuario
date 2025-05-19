package retoPragma.Microusuario.domain.util.exception;

public class NoAuthCreateEmployee extends RuntimeException {
    public NoAuthCreateEmployee() {
        super("Debes estar autenticado para crear empleados.");
    }
}
