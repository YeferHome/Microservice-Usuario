package retoPragma.Microusuario.application.dto;

import lombok.*;

@Setter
@Getter
public class LoginRequestDto {
    private String correo;
    private String clave;
}
