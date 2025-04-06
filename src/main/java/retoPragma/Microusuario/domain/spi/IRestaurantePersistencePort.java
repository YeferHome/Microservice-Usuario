package retoPragma.Microusuario.domain.spi;

import retoPragma.Microusuario.domain.model.Restaurante;

public interface IRestaurantePersistencePort {

    void saveRestaurante(Restaurante restaurante);
}
