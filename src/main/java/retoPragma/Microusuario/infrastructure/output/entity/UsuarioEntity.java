package retoPragma.Microusuario.infrastructure.output.entity;


import jakarta.persistence.*;
import lombok.*;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;


import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String apellido;
    private Long documentoDeIdentidad;
    private String celular;
    private LocalDateTime fechaNacimiento;
    private String correo;
    private String clave;
    private RolesPlazoleta rol;

}
