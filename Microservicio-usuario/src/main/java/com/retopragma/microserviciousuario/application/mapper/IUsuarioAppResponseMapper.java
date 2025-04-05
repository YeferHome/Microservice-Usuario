package com.retopragma.microserviciousuario.application.mapper;

import com.retopragma.microserviciousuario.application.dto.UsuarioResponseDto;
import com.retopragma.microserviciousuario.domain.model.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUsuarioAppResponseMapper {
    UsuarioResponseDto toUsuarioResponseDto(Usuario usuario);
}
