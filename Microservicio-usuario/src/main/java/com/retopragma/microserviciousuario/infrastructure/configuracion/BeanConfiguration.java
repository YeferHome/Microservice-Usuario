package com.retopragma.microserviciousuario.infrastructure.configuracion;

import com.retopragma.microserviciousuario.domain.UseCase.UsuarioUseCase;
import com.retopragma.microserviciousuario.domain.api.IUsuarioServicePort;
import com.retopragma.microserviciousuario.domain.spi.IUsuarioPersistencePort;
import com.retopragma.microserviciousuario.infrastructure.output.adapter.UsuarioJpaAdapter;
import com.retopragma.microserviciousuario.infrastructure.output.mapper.IUsuarioEntityMapper;
import com.retopragma.microserviciousuario.infrastructure.output.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioEntityMapper usuarioEntityMapper;

    @Bean
    public IUsuarioPersistencePort usuarioPersistencePort(){
        return new UsuarioJpaAdapter(usuarioRepository,usuarioEntityMapper);
    }

    @Bean
    public IUsuarioServicePort usuarioServicePort(){
        return new UsuarioUseCase(usuarioPersistencePort());
    }


}
