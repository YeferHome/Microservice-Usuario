package com.retopragma.microserviciousuario.application.dto;

import com.retopragma.microserviciousuario.domain.model.RolesPlazoleta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioAppRequestDto {
    private String nombre;
    private String apellido;
    private Long documentoDeIdentidad;
    private String celular;
    private LocalDate FechaNacimiento;
    private String correo;
    private String clave;
    private RolesPlazoleta rol;}
