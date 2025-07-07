package retoPragma.Microusuario.infrastructure.input.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import retoPragma.Microusuario.infrastructure.configuracion.feign.FeignClientConfig;
import retoPragma.Microusuario.infrastructure.input.Client.response.RestaurantIdResponseDto;

@FeignClient(name = "ms-plazoleta", url = "http://localhost:8083/restaurantApp", configuration = FeignClientConfig.class)
public interface PlazoletaFeignClient {

    @GetMapping("/{id}/restaurant")
    RestaurantIdResponseDto getRestaurantId(@PathVariable("id") Long id);

    @PutMapping("/update/{userId}/restaurant/{restaurantId}")
    void assignRestaurantToUser(@PathVariable("userId") Long userId, @PathVariable("restaurantId") Long restaurantId);

}