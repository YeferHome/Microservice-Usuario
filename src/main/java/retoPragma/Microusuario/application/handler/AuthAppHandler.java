package retoPragma.Microusuario.application.handler;

import retoPragma.Microusuario.application.dto.RegisterRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import retoPragma.Microusuario.application.dto.LoginRequestDto;
import retoPragma.Microusuario.application.dto.LoginResponseDto;
import retoPragma.Microusuario.application.mapper.IUserAppRequestMapper;
import retoPragma.Microusuario.domain.api.IUserServicePort;
import retoPragma.Microusuario.domain.model.User;
import retoPragma.Microusuario.domain.util.exception.PasswordErrorException;
import retoPragma.Microusuario.infrastructure.configuracion.jwt.JwtService;


@Service
@AllArgsConstructor
public class AuthAppHandler implements IAuthAppHandler {

    private final IUserServicePort userServicePort;
    private final IUserAppRequestMapper userAppRequestMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userServicePort.findUserByEmail(loginRequestDto.getCorreo());

        if (!passwordEncoder.matches(loginRequestDto.getClave(), user.getClave())) {
            throw new PasswordErrorException();
        }
        String jwtToken = jwtService.generate(user);

        return new LoginResponseDto(jwtToken);
    }

    @Override
    public void register(RegisterRequestDto registerRequestDto) {
        User user = userAppRequestMapper.toRegister(registerRequestDto);
        userServicePort.saveRegister(user);
    }

}

