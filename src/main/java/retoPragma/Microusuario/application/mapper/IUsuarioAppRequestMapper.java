package retoPragma.Microusuario.application.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import retoPragma.Microusuario.application.dto.EmpleadoResponseDto;
import retoPragma.Microusuario.application.dto.UsuarioAppRequestDto;
import retoPragma.Microusuario.domain.model.Usuario;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUsuarioAppRequestMapper {

    Usuario toUsuario(UsuarioAppRequestDto usuarioAppRequestDto);
}
