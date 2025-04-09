package retoPragma.Microusuario.domain.spi;


import retoPragma.Microusuario.domain.model.Usuario;

public interface IUsuarioPersistencePort {

    void saveUsuario(Usuario usuario);
    String findRolById(Long id);
    Usuario findByCorreo(String correo);
}
