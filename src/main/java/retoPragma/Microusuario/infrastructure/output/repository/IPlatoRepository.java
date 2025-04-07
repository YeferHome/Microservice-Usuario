package retoPragma.Microusuario.infrastructure.output.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retoPragma.Microusuario.infrastructure.output.entity.PlatoEntity;

@Repository
public interface IPlatoRepository extends JpaRepository<PlatoEntity, Long> {


}
