package retoPragma.Microusuario.infrastructure.output.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retoPragma.Microusuario.domain.spi.IRestaurantPlazoletaFeignPort;
import retoPragma.Microusuario.infrastructure.input.Client.PlazoletaFeignClient;

@Component
@RequiredArgsConstructor
public class RestaurantPlazoletaFeignAdapter implements IRestaurantPlazoletaFeignPort {

    private final PlazoletaFeignClient plazoletaFeignClient;

    @Override
    public Long getRestaurantId(Long id) {
        return plazoletaFeignClient.getRestaurantId(id).getId();
    }
}
