package retoPragma.Microusuario.domain.api;


import retoPragma.Microusuario.domain.model.User;

public interface IUserServicePort {
    void saveUser(User user);
    String findRolById(long id);
    User findUserByEmail(String email);
    void saveRegister(User user);
}
