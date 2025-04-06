package retoPragma.Microusuario.application.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import retoPragma.Microusuario.application.dto.RestauranteAppResponseDto;
import retoPragma.Microusuario.domain.model.Restaurante;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteAppResponseMapper {
    RestauranteAppResponseDto toRestauranteAppResponseDto(Restaurante restaurante);
}
