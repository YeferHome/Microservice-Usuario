package retoPragma.Microusuario.infrastructure.configuracion;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import retoPragma.Microusuario.domain.UseCase.UserUseCase;
import retoPragma.Microusuario.domain.api.IUserServicePort;
import retoPragma.Microusuario.domain.spi.IUserPersistencePort;
import retoPragma.Microusuario.domain.spi.ISecurityServicePort;
import retoPragma.Microusuario.domain.spi.ISmallSquareServicePort;
import retoPragma.Microusuario.infrastructure.output.adapter.UserJpaAdapter;
import retoPragma.Microusuario.infrastructure.output.adapter.SecurityServiceAdapter;
import retoPragma.Microusuario.infrastructure.output.mapper.IUserEntityMapper;
import retoPragma.Microusuario.infrastructure.output.repository.IUserRepository;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository usuarioRepository;
    private final IUserEntityMapper usuarioEntityMapper;
    private final ISmallSquareServicePort plazoletaFeignClient;

    @Bean
    public IUserPersistencePort usuarioPersistencePort() {
        return new UserJpaAdapter(usuarioRepository, usuarioEntityMapper);
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
    public IUserServicePort usuarioServicePort() {
        return new UserUseCase(usuarioPersistencePort(), securityServicePort(), plazoletaFeignClient);
    }
}