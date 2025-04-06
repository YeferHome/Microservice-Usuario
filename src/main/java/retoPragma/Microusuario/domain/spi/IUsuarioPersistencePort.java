package retoPragma.Microusuario.domain.spi;


import retoPragma.Microusuario.domain.model.Usuario;

public interface IUsuarioPersistencePort {
    void savePropietario(Usuario usuario);
    Usuario findById(Long id);
}
