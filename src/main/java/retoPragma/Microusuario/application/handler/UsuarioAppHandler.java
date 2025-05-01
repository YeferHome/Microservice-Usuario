package retoPragma.Microusuario.application.handler;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import retoPragma.Microusuario.application.dto.UsuarioAppRequestDto;
import retoPragma.Microusuario.application.mapper.IUsuarioAppRequestMapper;
import retoPragma.Microusuario.domain.api.IUsuarioServicePort;
import retoPragma.Microusuario.domain.model.Usuario;

@Service
@RequiredArgsConstructor
public class  UsuarioAppHandler implements IUsuarioAppHandler {

    private final IUsuarioServicePort usuarioServicePort;
    private final IUsuarioAppRequestMapper usuarioAppRequestMapper;


    @Override
    public void saveUsuarioInUsuarioApp(UsuarioAppRequestDto usuarioAppRequestDto) {
        Usuario usuario = usuarioAppRequestMapper.toUsuario(usuarioAppRequestDto);
        usuarioServicePort.saveUsuario(usuario);
    }

    @Override
    public void savePropietarioInUsuarioApp(UsuarioAppRequestDto usuarioAppRequestDto){
        Usuario propietario =  usuarioAppRequestMapper.toUsuario(usuarioAppRequestDto);
        usuarioServicePort.saveUsuario(propietario);
    }

    @Override
    public void saveEmpleadoInUsuarioApp (UsuarioAppRequestDto usuarioAppRequestDto){
        Usuario empleado =  usuarioAppRequestMapper.toUsuario(usuarioAppRequestDto);
        usuarioServicePort.saveUsuario(empleado);
    }


    @Override
    public String findRolById(Long id) {
        return usuarioServicePort.findRolById(id);
    }


}

