package retoPragma.Microusuario.application.handler;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import retoPragma.Microusuario.application.dto.UsuarioAppRequestDto;
import retoPragma.Microusuario.domain.api.IUsuarioServicePort;
import retoPragma.Microusuario.domain.model.Usuario;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioAppHandler implements IUsuarioAppHandler {

    private final IUsuarioServicePort usuarioServicePort;

    @Override
    public void saveUsuarioInUsuarioApp(UsuarioAppRequestDto usuarioAppRequestDto) {
        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioAppRequestDto.getNombre());
        usuario.setApellido(usuarioAppRequestDto.getApellido());
        usuario.setDocumentoDeIdentidad(usuarioAppRequestDto.getDocumentoDeIdentidad());
        usuario.setCelular(usuarioAppRequestDto.getCelular());
        usuario.setFechaNacimiento(usuarioAppRequestDto.getFechaNacimiento());
        usuario.setCorreo(usuarioAppRequestDto.getCorreo());
        usuario.setClave(usuarioAppRequestDto.getClave());

        usuarioServicePort.savePropietario(usuario);
    }
}
