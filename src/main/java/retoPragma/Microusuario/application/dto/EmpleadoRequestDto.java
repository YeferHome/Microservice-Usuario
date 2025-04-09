package retoPragma.Microusuario.application.dto;

import lombok.*;

@Getter
@Setter
public class EmpleadoRequestDto {

    private String nombreEmpleado;
    private String apellidoEmpleado;
    private Long documentoDeIdentidadEmpleado;
    private String celularEmpleado;
    private String correo;
    private String clave;
}
