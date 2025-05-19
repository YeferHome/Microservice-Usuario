package retoPragma.Microusuario.infrastructure.output.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retoPragma.Microusuario.domain.spi.ISmallSquareServicePort;
import retoPragma.Microusuario.infrastructure.input.Client.PlazoletaFeignClient;

@Component
@RequiredArgsConstructor
public class SmallSquareServiceAdapter implements ISmallSquareServicePort {

    private final PlazoletaFeignClient plazoletaFeignClient;


    @Override
    public Long obtainRestaurantId(Long idRestaurant) {
        return plazoletaFeignClient.obtenerRestaurateId(idRestaurant);
    }
}
