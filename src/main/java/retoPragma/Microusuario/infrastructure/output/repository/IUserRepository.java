package retoPragma.Microusuario.infrastructure.output.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.infrastructure.output.entity.UserEntity;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByIdentificationNumber(long identificationNumber);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByRol(RolesPlazoleta rol);

}
