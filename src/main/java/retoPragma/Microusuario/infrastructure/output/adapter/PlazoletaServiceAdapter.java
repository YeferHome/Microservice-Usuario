package retoPragma.Microusuario.infrastructure.output.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retoPragma.Microusuario.domain.spi.IPlazoletaServicePort;
import retoPragma.Microusuario.infrastructure.input.Client.PlazoletaFeignClient;

@Component
@RequiredArgsConstructor
public class PlazoletaServiceAdapter implements IPlazoletaServicePort {

    private final PlazoletaFeignClient plazoletaFeignClient;


    @Override
    public Long obtenerRestaurateId(Long id) {
        return plazoletaFeignClient.obtenerRestaurateId(id);
    }
}
