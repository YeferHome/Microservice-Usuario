package com.retopragma.microserviciousuario.application.mapper;

import com.retopragma.microserviciousuario.application.dto.UsuarioAppRequestDto;
import com.retopragma.microserviciousuario.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUsuarioAppRequestMapper {
    Usuario toUsuario(UsuarioAppRequestDto usuarioAppRequestDto);
}
