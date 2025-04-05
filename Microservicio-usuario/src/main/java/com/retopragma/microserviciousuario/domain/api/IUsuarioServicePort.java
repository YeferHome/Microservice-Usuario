package com.retopragma.microserviciousuario.domain.api;

import com.retopragma.microserviciousuario.domain.model.Usuario;

public interface IUsuarioServicePort {
    void savePropietario(Usuario usuario);
}
