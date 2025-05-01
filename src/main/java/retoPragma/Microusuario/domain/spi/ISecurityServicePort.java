package retoPragma.Microusuario.domain.spi;

public interface ISecurityServicePort {
    String encodePassword(String rawPassword);
    String getAuthenticatedUsername();
    String getAuthenticatedAuthority();
}
