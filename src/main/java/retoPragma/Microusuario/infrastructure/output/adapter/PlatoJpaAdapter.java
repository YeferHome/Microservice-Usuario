package retoPragma.Microusuario.infrastructure.output.adapter;

import lombok.RequiredArgsConstructor;
import retoPragma.Microusuario.domain.model.Plato;
import retoPragma.Microusuario.domain.spi.IPlatoPersistencePort;
import retoPragma.Microusuario.infrastructure.output.mapper.IPlatoEntityMapper;
import retoPragma.Microusuario.infrastructure.output.repository.IPlatoRepository;

@RequiredArgsConstructor
public class PlatoJpaAdapter implements IPlatoPersistencePort {
    private final IPlatoRepository platoRepository;
    private final IPlatoEntityMapper platoEntityMapper;

    @Override
    public void savePlato(Plato plato) {
        platoRepository.save(platoEntityMapper.toPlatoEntity(plato));
    }
}
