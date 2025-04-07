package retoPragma.Microusuario.application.handler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import retoPragma.Microusuario.application.dto.PlatoAppRequestDto;
import retoPragma.Microusuario.domain.api.IPlatoServicePort;
import retoPragma.Microusuario.domain.model.Plato;

@Service
@RequiredArgsConstructor
@Transactional
public class PlatoAppHandler implements IPlatoAppHandler {

    private final IPlatoServicePort platoServicePort;


    @Override
    public void savePlatoInPlatoApp(PlatoAppRequestDto platoAppRequestDto) {
        Plato plato = new Plato();
        plato.setNombrePlato(platoAppRequestDto.getNombrePlato());
        plato.setDescripcionPlato(platoAppRequestDto.getDescripcionPlato());
        plato.setPrecioPlato(platoAppRequestDto.getPrecioPlato());
        plato.setUrlPlato(platoAppRequestDto.getUrlPlato());
        plato.setCategoriaPlato(platoAppRequestDto.getCategoriaPlato());
        plato.setIdRestaurante(platoAppRequestDto.getIdRestaurante());
        plato.setActivo(platoAppRequestDto.getActivo());
        platoServicePort.savePlato(plato);
    }
}