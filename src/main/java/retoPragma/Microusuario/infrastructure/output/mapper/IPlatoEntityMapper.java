package retoPragma.Microusuario.infrastructure.output.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import retoPragma.Microusuario.domain.model.Plato;
import retoPragma.Microusuario.infrastructure.output.entity.PlatoEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoEntityMapper {
    PlatoEntity toPlatoEntity(Plato plato);

    Plato toPlato(PlatoEntity platoEntity);
}