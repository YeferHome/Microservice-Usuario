package com.retopragma.microserviciousuario.domain.spi;

import com.retopragma.microserviciousuario.domain.model.Usuario;

public interface IUsuarioPersistencePort {
    void savePropietario(Usuario usuario);
}
