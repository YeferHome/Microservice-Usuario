package retoPragma.Microusuario.infrastructure.output.entity;


import jakarta.persistence.*;
import lombok.*;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;


import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Long documentoDeIdentidad;
    private String celular;
    private LocalDate fechaNacimiento;
    private String correo;
    private String clave;
    @Enumerated(EnumType.STRING)
    private RolesPlazoleta rol;
    private Long idRestaurante;

}
