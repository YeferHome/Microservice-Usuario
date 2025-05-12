package retoPragma.Microusuario.infrastructure.input.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import retoPragma.Microusuario.infrastructure.configuracion.feign.FeignClientConfig;

@FeignClient(name = "ms-plazoleta", url = "http://localhost:8083/restauranteApp", configuration = FeignClientConfig.class)
public interface PlazoletaFeignClient {

    @GetMapping("/{id}/restaurante")
    Long obtenerRestaurateId(@PathVariable("id") Long id);

}