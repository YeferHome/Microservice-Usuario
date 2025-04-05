package com.retopragma.microserviciousuario.application.dto;

import com.retopragma.microserviciousuario.domain.model.RolesPlazoleta;

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
}
