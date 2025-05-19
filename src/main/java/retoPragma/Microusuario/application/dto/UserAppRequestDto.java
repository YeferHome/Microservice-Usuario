package retoPragma.Microusuario.application.dto;



import lombok.Getter;
import lombok.Setter;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;

import java.time.LocalDate;

@Getter
@Setter
public class UserAppRequestDto {
    private Long id;
    private String name;
    private String lastname;
    private Long identificationNumber;
    private String numberPhone;
    private LocalDate dateOfBirth;
    private String email;
    private String clave;
    private RolesPlazoleta rol;
    private Long idRestaurant;
}
