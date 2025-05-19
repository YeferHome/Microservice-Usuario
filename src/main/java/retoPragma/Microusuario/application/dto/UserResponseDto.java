package retoPragma.Microusuario.application.dto;

import retoPragma.Microusuario.domain.model.RolesPlazoleta;

import java.time.LocalDate;

public class UserResponseDto {
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
