package retoPragma.Microusuario.domain.UseCase;

import retoPragma.Microusuario.domain.api.IRestauranteServicePort;
import retoPragma.Microusuario.domain.model.Restaurante;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.domain.model.Usuario;
import retoPragma.Microusuario.domain.spi.IRestaurantePersistencePort;
import retoPragma.Microusuario.domain.spi.IUsuarioPersistencePort;
import retoPragma.Microusuario.infrastructure.exception.BusinessException;

public class RestauranteUseCase implements IRestauranteServicePort {

    private final IRestaurantePersistencePort restaurantePersistencePort;
    private final IUsuarioPersistencePort usuarioPersistencePort;

    public RestauranteUseCase(IRestaurantePersistencePort restaurantePersistencePort, IUsuarioPersistencePort usuarioPersistencePort) {
        this.restaurantePersistencePort = restaurantePersistencePort;
        this.usuarioPersistencePort = usuarioPersistencePort;
    }

    @Override
    public void saveRestaurante(Restaurante restaurante) {

        if (restaurante.getIdUsuario() == null) {
            throw new BusinessException("El id del usuario no puede ser nulo.");
        }

        Usuario usuario = usuarioPersistencePort.findById(restaurante.getIdUsuario());
        if (usuario == null) {
            throw new BusinessException("No existe un usuario con ese ID.");
        }
        if (usuario.getRol() != RolesPlazoleta.PROPIETARIO) {
            throw new BusinessException("El usuario no posee rol de PROPIETARIO.");
        }

        if (restaurante.getNit() == null || restaurante.getNit() <= 0) {
            throw new BusinessException("Documento de identidad debe ser un número positivo.");
        }

        if (!esTelefonoRestauranteValido(restaurante.getTelefonoRestaurante())) {
            throw new BusinessException("Teléfono inválido; máximo 13 dígitos y debe iniciar con '+57'.");
        }

        if (!esNombreRestauranteValido(restaurante.getNombreRestaurante())) {
            throw new BusinessException("El nombre del restaurante no puede ser solo números. Ejemplo: 'Mi Restaurante 21'");
        }

        restaurantePersistencePort.saveRestaurante(restaurante);
    }

    private boolean esTelefonoRestauranteValido(String telefonoRestaurante) {
        if (telefonoRestaurante == null) return false;

        return telefonoRestaurante.matches("\\+?\\d{1,13}");
    }

    private boolean esNombreRestauranteValido(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        return !nombre.matches("\\d+");
    }
}
