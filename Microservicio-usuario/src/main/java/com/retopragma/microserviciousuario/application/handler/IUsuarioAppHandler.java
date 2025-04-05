package com.retopragma.microserviciousuario.application.handler;

import com.retopragma.microserviciousuario.application.dto.UsuarioAppRequestDto;

public interface IUsuarioAppHandler {
    void saveUsuarioInUsuarioApp(UsuarioAppRequestDto usuarioAppRequestDto);
}
