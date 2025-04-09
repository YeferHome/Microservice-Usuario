package retoPragma.Microusuario.application.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmpleadoResponseDto {
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private Long documentoDeIdentidadEmpleado;
    private String celularEmpleado;
    private String correo;
    private String clave;

}
