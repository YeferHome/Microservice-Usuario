package retoPragma.Microusuario.infrastructure.configuracion;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import retoPragma.Microusuario.domain.UseCase.UsuarioUseCase;
import retoPragma.Microusuario.domain.api.IUsuarioServicePort;
import retoPragma.Microusuario.domain.spi.IUsuarioPersistencePort;
import retoPragma.Microusuario.domain.spi.ISecurityServicePort;
import retoPragma.Microusuario.domain.spi.IPlazoletaServicePort;
import retoPragma.Microusuario.infrastructure.output.adapter.UsuarioJpaAdapter;
import retoPragma.Microusuario.infrastructure.output.adapter.SecurityServiceAdapter;
import retoPragma.Microusuario.infrastructure.output.mapper.IUsuarioEntityMapper;
import retoPragma.Microusuario.infrastructure.output.repository.IUsuarioRepository;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioEntityMapper usuarioEntityMapper;
    private final IPlazoletaServicePort plazoletaFeignClient;

    @Bean
    public IUsuarioPersistencePort usuarioPersistencePort() {
        return new UsuarioJpaAdapter(usuarioRepository, usuarioEntityMapper);
    }

    @Bean
    @Primary
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ISecurityServicePort securityServicePort() {
        return new SecurityServiceAdapter(bCryptPasswordEncoder());
    }

    @Bean
    public IUsuarioServicePort usuarioServicePort() {
        return new UsuarioUseCase(usuarioPersistencePort(), securityServicePort(), plazoletaFeignClient);
    }
}