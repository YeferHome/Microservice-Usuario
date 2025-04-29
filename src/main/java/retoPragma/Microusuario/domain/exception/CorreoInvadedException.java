package retoPragma.Microusuario.domain.exception;

public class CorreoInvadedException extends RuntimeException {
    public CorreoInvadedException() {
        super("Correo no válido, revise la estructura (ej. usuario@dominio.com)");
    }
}
