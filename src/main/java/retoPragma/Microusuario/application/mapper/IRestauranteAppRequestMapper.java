package retoPragma.Microusuario.application.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import retoPragma.Microusuario.application.dto.RestauranteAppRequestDto;
import retoPragma.Microusuario.domain.model.Restaurante;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteAppRequestMapper {
    Restaurante toRestaurante(RestauranteAppRequestDto restauranteAppRequestDto);
}
