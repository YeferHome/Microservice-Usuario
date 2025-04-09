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
    public void saveUsuario(Usuario usuario) {
        if (usuarioRepository.findByDocumentoDeIdentidad(usuario.getDocumentoDeIdentidad()).isPresent()) {
            throw new BusinessException("El usuario con este documento ya existe");
        }
        usuarioRepository.save(usuarioEntityMapper.toUsuarioEntity(usuario));
    }

    @Override
    public String findRolById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioEntityMapper::toUsuario)
                .map(usuario -> usuario.getRol().toString())
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + id + " no encontrado"));
    }

    @Override
    public Usuario findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
                .map(usuarioEntityMapper::toUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario con correo " + correo + " no encontrado"));
    }
}