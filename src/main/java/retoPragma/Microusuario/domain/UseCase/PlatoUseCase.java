package retoPragma.Microusuario.domain.UseCase;

import retoPragma.Microusuario.domain.api.IPlatoServicePort;
import retoPragma.Microusuario.domain.model.Plato;
import retoPragma.Microusuario.domain.spi.IPlatoPersistencePort;

public class PlatoUseCase implements IPlatoServicePort {

    private final IPlatoPersistencePort platoPersistencePort;

    public PlatoUseCase(IPlatoPersistencePort platoPersistencePort) {
        this.platoPersistencePort = platoPersistencePort;
    }

    @Override
    public void savePlato(Plato plato) {

        platoPersistencePort.savePlato(plato);
    }
}
