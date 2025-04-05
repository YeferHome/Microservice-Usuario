package com.retopragma.microserviciousuario.infrastructure.output.entity;

import com.retopragma.microserviciousuario.domain.model.RolesPlazoleta;
import jakarta.persistence.*;
import lombok.*;



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
    private LocalDateTime FechaNacimiento;
    private String correo;
    private String clave;
    private RolesPlazoleta rol;

}
