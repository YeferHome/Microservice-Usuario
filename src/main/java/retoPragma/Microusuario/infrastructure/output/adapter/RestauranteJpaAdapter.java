package retoPragma.Microusuario.infrastructure.output.adapter;


import lombok.RequiredArgsConstructor;
import retoPragma.Microusuario.domain.model.Restaurante;
import retoPragma.Microusuario.domain.spi.IRestaurantePersistencePort;
import retoPragma.Microusuario.infrastructure.output.mapper.IRestauranteEntityMapper;
import retoPragma.Microusuario.infrastructure.output.repository.IRestauranteRepository;

@RequiredArgsConstructor
public class RestauranteJpaAdapter implements IRestaurantePersistencePort {
    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;

    @Override
    public void saveRestaurante(Restaurante restaurante) {
        restauranteRepository.save(restauranteEntityMapper.toRestauranteEntity(restaurante));
    }
}
