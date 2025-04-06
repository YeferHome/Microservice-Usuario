package retoPragma.Microusuario.infrastructure.configuracion;



import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retoPragma.Microusuario.domain.UseCase.UsuarioUseCase;
import retoPragma.Microusuario.domain.api.IUsuarioServicePort;
import retoPragma.Microusuario.domain.spi.IUsuarioPersistencePort;
import retoPragma.Microusuario.infrastructure.output.adapter.UsuarioJpaAdapter;
import retoPragma.Microusuario.infrastructure.output.mapper.IUsuarioEntityMapper;
import retoPragma.Microusuario.infrastructure.output.repository.IUsuarioRepository;
@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioEntityMapper usuarioEntityMapper;

    @Bean
    public IUsuarioPersistencePort usuarioPersistencePort() {
        return new UsuarioJpaAdapter(usuarioRepository, usuarioEntityMapper);
    }

    @Bean
    public IUsuarioServicePort usuarioServicePort() {
        return new UsuarioUseCase(usuarioPersistencePort());
    }
}
