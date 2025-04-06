package retoPragma.Microusuario.application.mapper;


import org.mapstruct.*;
import retoPragma.Microusuario.application.dto.UsuarioResponseDto;
import retoPragma.Microusuario.domain.model.Usuario;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUsuarioAppResponseMapper {
    UsuarioResponseDto toUsuarioResponseDto(Usuario usuario);
}
