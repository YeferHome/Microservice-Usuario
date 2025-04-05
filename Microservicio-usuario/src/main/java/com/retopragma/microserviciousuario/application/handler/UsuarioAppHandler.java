package com.retopragma.microserviciousuario.application.handler;

import com.retopragma.microserviciousuario.application.dto.UsuarioAppRequestDto;
import com.retopragma.microserviciousuario.domain.api.IUsuarioServicePort;
import com.retopragma.microserviciousuario.domain.model.Usuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioAppHandler implements IUsuarioAppHandler {

    private final IUsuarioServicePort usuarioServicePort;

    @Override
    public void saveUsuarioInUsuarioApp(UsuarioAppRequestDto usuarioAppRequestDto) {
        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioAppRequestDto.getNombre());
        usuario.setApellido(usuarioAppRequestDto.getApellido());
        usuario.setDocumentoDeIdentidad(usuarioAppRequestDto.getDocumentoDeIdentidad());
        usuario.setCelular(usuarioAppRequestDto.getCelular());
        usuario.setFechaNacimiento(usuarioAppRequestDto.getFechaNacimiento());
        usuario.setCorreo(usuarioAppRequestDto.getCorreo());
        usuario.setClave(usuarioAppRequestDto.getClave());

        usuarioServicePort.savePropietario(usuario);
    }
}
