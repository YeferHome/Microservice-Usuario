package retoPragma.Microusuario.application.handler;

import retoPragma.Microusuario.application.dto.LoginRequestDto;
import retoPragma.Microusuario.application.dto.LoginResponseDto;
import retoPragma.Microusuario.application.dto.RegisterRequestDto;

public interface IAuthAppHandler {


    LoginResponseDto login(LoginRequestDto loginRequestDto);

    void register(RegisterRequestDto registerRequestDto);
}
