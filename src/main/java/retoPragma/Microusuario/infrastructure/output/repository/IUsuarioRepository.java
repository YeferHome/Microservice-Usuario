package retoPragma.Microusuario.infrastructure.output.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retoPragma.Microusuario.infrastructure.output.entity.UsuarioEntity;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByDocumentoDeIdentidad(long DocumentoIdentidad);


}
