package retoPragma.Microusuario.domain.util.exception;

public class DocumentException extends RuntimeException {
    public DocumentException() {
        super("Documento de identidad debe ser un número positivo.");
    }
}
