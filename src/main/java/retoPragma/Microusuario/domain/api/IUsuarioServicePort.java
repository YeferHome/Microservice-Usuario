package retoPragma.Microusuario.domain.api;


import retoPragma.Microusuario.domain.model.Usuario;

public interface IUsuarioServicePort {
    void saveUsuario(Usuario usuario);
    String findRolById(long id);
    Usuario findUsuarioByCorreo(String correo);
    void saveRegister(Usuario usuario);
}
