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
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private Long identificationNumber;
    private String numberPhone;
    private LocalDate dateOfBirth;
    private String email;
    private String clave;
    @Enumerated(EnumType.STRING)
    private RolesPlazoleta rol;
    private Long idRestaurant;

}
