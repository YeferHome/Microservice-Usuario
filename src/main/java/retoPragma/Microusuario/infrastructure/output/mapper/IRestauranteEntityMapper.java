package retoPragma.Microusuario.infrastructure.output.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import retoPragma.Microusuario.domain.model.Restaurante;
import retoPragma.Microusuario.infrastructure.output.entity.RestauranteEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteEntityMapper {

    RestauranteEntity toRestauranteEntity(Restaurante restaurante);
    Restaurante toRestaurante(RestauranteEntity restauranteEntity);
}