package retoPragma.Microusuario.infrastructure.configuracion;



import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retoPragma.Microusuario.domain.UseCase.PlatoUseCase;
import retoPragma.Microusuario.domain.UseCase.RestauranteUseCase;
import retoPragma.Microusuario.domain.UseCase.UsuarioUseCase;
import retoPragma.Microusuario.domain.api.IPlatoServicePort;
import retoPragma.Microusuario.domain.api.IRestauranteServicePort;
import retoPragma.Microusuario.domain.api.IUsuarioServicePort;
import retoPragma.Microusuario.domain.spi.IPlatoPersistencePort;
import retoPragma.Microusuario.domain.spi.IRestaurantePersistencePort;
import retoPragma.Microusuario.domain.spi.IUsuarioPersistencePort;
import retoPragma.Microusuario.infrastructure.output.adapter.PlatoJpaAdapter;
import retoPragma.Microusuario.infrastructure.output.adapter.RestauranteJpaAdapter;
import retoPragma.Microusuario.infrastructure.output.adapter.UsuarioJpaAdapter;
import retoPragma.Microusuario.infrastructure.output.mapper.IPlatoEntityMapper;
import retoPragma.Microusuario.infrastructure.output.mapper.IRestauranteEntityMapper;
import retoPragma.Microusuario.infrastructure.output.mapper.IUsuarioEntityMapper;
import retoPragma.Microusuario.infrastructure.output.repository.IPlatoRepository;
import retoPragma.Microusuario.infrastructure.output.repository.IRestauranteRepository;
import retoPragma.Microusuario.infrastructure.output.repository.IUsuarioRepository;
@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioEntityMapper usuarioEntityMapper;
    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;
    private final IPlatoRepository platoRepository;
    private final IPlatoEntityMapper platoEntityMapper;


    @Bean
    public IUsuarioPersistencePort usuarioPersistencePort() {
        return new UsuarioJpaAdapter(usuarioRepository, usuarioEntityMapper);
    }

    @Bean
    public IUsuarioServicePort usuarioServicePort() {
        return new UsuarioUseCase(usuarioPersistencePort());
    }


    @Bean
    public IRestaurantePersistencePort restaurantePersistencePort(){
        return new RestauranteJpaAdapter(restauranteRepository,restauranteEntityMapper);
    }

    @Bean
    public IRestauranteServicePort restauranteServicePort(){
        return new RestauranteUseCase(restaurantePersistencePort(), usuarioPersistencePort());
    }
    @Bean
    public IPlatoPersistencePort platoPersistencePort() {
        return new PlatoJpaAdapter(platoRepository, platoEntityMapper);
    }

    @Bean
    public IPlatoServicePort platoServicePort() {
        return new PlatoUseCase(platoPersistencePort());
    }

}
