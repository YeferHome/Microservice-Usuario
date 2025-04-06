package retoPragma.Microusuario.application.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import retoPragma.Microusuario.application.dto.RestauranteAppRequestDto;
import retoPragma.Microusuario.domain.api.IRestauranteServicePort;
import retoPragma.Microusuario.domain.model.Restaurante;

@Service
@RequiredArgsConstructor
@Transactional
public class RestauranteAppHandler implements IRestauranteAppHandler {

    private final IRestauranteServicePort restauranteServicePort;

    @Override
    public void saveRestauranteInRestauranteApp(RestauranteAppRequestDto restauranteAppRequestDto) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNombreRestaurante(restauranteAppRequestDto.getNombreRestaurante());
        restaurante.setNit(restauranteAppRequestDto.getNit());
        restaurante.setDireccion(restauranteAppRequestDto.getDireccion());
        restaurante.setTelefonoRestaurante(restauranteAppRequestDto.getTelefonoRestaurante());
        restaurante.setUrlLogo(restauranteAppRequestDto.getUrlLogo());
        restaurante.setIdUsuario(restauranteAppRequestDto.getIdUsuario());

        restauranteServicePort.saveRestaurante(restaurante);
    }
}