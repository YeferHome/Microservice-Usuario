package retoPragma.Microusuario.infrastructure.output.adapter;

import lombok.RequiredArgsConstructor;

import retoPragma.Microusuario.domain.exception.DocumentNoExistException;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.domain.model.Usuario;
import retoPragma.Microusuario.domain.spi.IUsuarioPersistencePort;
import retoPragma.Microusuario.infrastructure.output.mapper.IUsuarioEntityMapper;
import retoPragma.Microusuario.infrastructure.output.repository.IUsuarioRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class UsuarioJpaAdapter implements IUsuarioPersistencePort {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioEntityMapper usuarioEntityMapper;
    @Override
    public void saveUsuario(Usuario usuario) {
        if (usuarioRepository.findByDocumentoDeIdentidad(usuario.getDocumentoDeIdentidad()).isPresent()) {
            throw new DocumentNoExistException();
        }
        usuarioRepository.save(usuarioEntityMapper.toUsuarioEntity(usuario));
    }
    @Override
    public String findRolById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioEntityMapper::toUsuario)
                .map(usuario -> usuario.getRol().toString())
                .orElse(null);
    }

    @Override
    public Usuario findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
                .map(usuarioEntityMapper::toUsuario)
                .orElse(null);
    }

    @Override
    public Optional<Usuario> findByRol(RolesPlazoleta rol) {
        return usuarioRepository.findByRol(rol)
                .map(usuarioEntityMapper::toUsuario);
    }

}
