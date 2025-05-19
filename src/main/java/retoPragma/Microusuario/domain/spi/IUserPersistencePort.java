package retoPragma.Microusuario.domain.spi;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.domain.model.User;


import java.util.Optional;

public interface IUserPersistencePort {

    void saveUser(User user);
    String findRolById(Long id);
    User findByEmail(String email);
    Optional<User> findByRol(RolesPlazoleta rol);


}
