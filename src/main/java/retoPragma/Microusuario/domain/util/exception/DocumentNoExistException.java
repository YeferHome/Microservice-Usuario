package retoPragma.Microusuario.domain.util.exception;

public class DocumentNoExistException extends RuntimeException {
    public DocumentNoExistException() {
        super("El usuario con este documento ya existe\"");
    }
}
