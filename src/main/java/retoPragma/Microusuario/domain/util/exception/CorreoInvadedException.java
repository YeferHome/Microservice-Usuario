package retoPragma.Microusuario.domain.util.exception;

public class CorreoInvadedException extends RuntimeException {
    public CorreoInvadedException() {
        super("Correo no válido, revise la estructura (ej. usuario@dominio.com)");
    }
}
