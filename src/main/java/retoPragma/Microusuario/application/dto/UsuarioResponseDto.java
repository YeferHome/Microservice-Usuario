package retoPragma.Microusuario.application.dto;

import retoPragma.Microusuario.domain.model.RolesPlazoleta;

import java.time.LocalDateTime;

public class UsuarioResponseDto {
    private String nombre;
    private String apellido;
    private Long documentoDeIdentidad;
    private String celular;
    private LocalDateTime FechaNacimiento;
    private String correo;
    private String clave;
    private RolesPlazoleta rol;
    private Long idRestaurante;
}
