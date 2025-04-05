package com.retopragma.microserviciousuario.infrastructure.output.repository;

import com.retopragma.microserviciousuario.infrastructure.output.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByDocumentoDeIdentidad(long DocumentoIdentidad);
}
