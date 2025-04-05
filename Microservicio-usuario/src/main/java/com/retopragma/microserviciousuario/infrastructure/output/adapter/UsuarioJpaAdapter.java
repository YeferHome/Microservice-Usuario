package com.retopragma.microserviciousuario.infrastructure.output.adapter;

import com.retopragma.microserviciousuario.domain.model.Usuario;
import com.retopragma.microserviciousuario.domain.spi.IUsuarioPersistencePort;
import com.retopragma.microserviciousuario.infrastructure.exception.BusinessException;
import com.retopragma.microserviciousuario.infrastructure.output.mapper.IUsuarioEntityMapper;
import com.retopragma.microserviciousuario.infrastructure.output.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioJpaAdapter implements IUsuarioPersistencePort {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioEntityMapper usuarioEntityMapper;


    @Override
    public void savePropietario(Usuario usuario) {
        if (usuarioRepository.findByDocumentoDeIdentidad(usuario.getDocumentoDeIdentidad()).isPresent()) {
            throw new BusinessException("El usuario con este documento ya existe");
        }
        usuarioRepository.save(usuarioEntityMapper.toUsuarioEntity(usuario));
    }
}
