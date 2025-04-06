package retoPragma.Microusuario.infrastructure.output.adapter;


import lombok.RequiredArgsConstructor;
import retoPragma.Microusuario.domain.model.Usuario;
import retoPragma.Microusuario.domain.spi.IUsuarioPersistencePort;
import retoPragma.Microusuario.infrastructure.exception.BusinessException;
import retoPragma.Microusuario.infrastructure.output.mapper.IUsuarioEntityMapper;
import retoPragma.Microusuario.infrastructure.output.repository.IUsuarioRepository;

@RequiredArgsConstructor
public class UsuarioJpaAdapter implements IUsuarioPersistencePort {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioEntityMapper usuarioEntityMapper;


    @Override
    public void savePropietario(Usuario usuario) {
        if (usuarioRepository.findByDocumentoDeIdentidad(usuario.getDocumentoDeIdentidad()).isPresent()) {
            throw new BusinessException("El usuario con este documento ya existe");
        }
        usuarioRepository.save(usuarioEntityMapper.toUsuarioEntity(usuario));
    }
    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).map(usuarioEntityMapper::toUsuario).orElse(null);
    }
}
