package retoPragma.Microusuario.application.handler;

import retoPragma.Microusuario.application.dto.RegisterRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import retoPragma.Microusuario.application.dto.LoginRequestDto;
import retoPragma.Microusuario.application.dto.LoginResponseDto;
import retoPragma.Microusuario.application.mapper.IUsuarioAppRequestMapper;
import retoPragma.Microusuario.domain.api.IUsuarioServicePort;
import retoPragma.Microusuario.domain.model.Usuario;
import retoPragma.Microusuario.infrastructure.configuracion.jwt.JwtService;
import retoPragma.Microusuario.infrastructure.exception.BusinessException;

@Service
@AllArgsConstructor
public class AuthAppHandler implements IAuthAppHandler {

    private final IUsuarioServicePort usuarioServicePort;
    private final IUsuarioAppRequestMapper usuarioAppRequestMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;




    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Usuario usuario = usuarioServicePort.findUsuarioByCorreo(loginRequestDto.getCorreo());

        if (!passwordEncoder.matches(loginRequestDto.getClave(), usuario.getClave())) {
            throw new BusinessException("Contraseña incorrecta");
        }

        String jwtToken = jwtService.generate(usuario);

        return new LoginResponseDto(jwtToken);    }

    @Override
    public void register(RegisterRequestDto registerRequestDto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(registerRequestDto.getNombre());
        usuario.setApellido(registerRequestDto.getApellido());
        usuario.setDocumentoDeIdentidad(registerRequestDto.getDocumentoDeIdentidad());
        usuario.setCelular(registerRequestDto.getCelular());
        usuario.setFechaNacimiento(registerRequestDto.getFechaNacimiento());
        usuario.setCorreo(registerRequestDto.getCorreo());
        usuario.setClave(passwordEncoder.encode(registerRequestDto.getClave()));
        usuario.setRol(registerRequestDto.getRol());

        usuarioServicePort.saveUsuario(usuario);
    }
}
