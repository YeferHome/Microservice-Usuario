package retoPragma.Microusuario.domain.spi;

import retoPragma.Microusuario.domain.model.Plato;

public interface IPlatoPersistencePort {
    void savePlato(Plato plato);
}
