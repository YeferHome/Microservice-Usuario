package retoPragma.Microusuario.domain.exception;

public class DocumentNoExistException extends RuntimeException {
    public DocumentNoExistException() {
        super("El usuario con este documento ya existe\"");
    }
}
