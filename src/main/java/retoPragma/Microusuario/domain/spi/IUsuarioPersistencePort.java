package retoPragma.Microusuario.domain.spi;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.domain.model.Usuario;


import java.util.Optional;

public interface IUsuarioPersistencePort {

    void saveUsuario(Usuario usuario);
    String findRolById(Long id);
    Usuario findByCorreo(String correo);
    Optional<Usuario> findByRol(RolesPlazoleta rol);


}
