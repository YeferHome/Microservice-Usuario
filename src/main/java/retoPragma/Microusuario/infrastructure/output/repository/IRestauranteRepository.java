package retoPragma.Microusuario.infrastructure.output.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import retoPragma.Microusuario.infrastructure.output.entity.RestauranteEntity;

public interface IRestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
}