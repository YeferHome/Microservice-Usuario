package retoPragma.Microusuario.application.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioAppRequestDto {
    private String nombre;
    private String apellido;
    private Long documentoDeIdentidad;
    private String celular;
    private LocalDate fechaNacimiento;
    private String correo;
    private String clave;
    private RolesPlazoleta rol;
}
