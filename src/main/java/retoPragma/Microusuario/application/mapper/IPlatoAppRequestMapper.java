package retoPragma.Microusuario.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import retoPragma.Microusuario.application.dto.PlatoAppRequestDto;
import retoPragma.Microusuario.domain.model.Plato;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoAppRequestMapper {

    Plato toPlato(PlatoAppRequestDto platoAppRequestDto);

}