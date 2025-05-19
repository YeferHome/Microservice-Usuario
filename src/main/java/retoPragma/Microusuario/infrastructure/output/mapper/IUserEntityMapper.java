package retoPragma.Microusuario.infrastructure.output.mapper;

import org.springframework.context.annotation.Configuration;
import retoPragma.Microusuario.domain.model.User;
import retoPragma.Microusuario.infrastructure.output.entity.UserEntity;

@Configuration
public class IUserEntityMapper {

    public UserEntity toUsuarioEntity(User user) {
        if (user == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setLastname(user.getLastName());
        entity.setIdentificationNumber(user.getIdentificationNumber());
        entity.setNumberPhone(user.getNumberPhone());
        entity.setDateOfBirth(user.getDateOfBirth());
        entity.setEmail(user.getEmail());
        entity.setClave(user.getClave());
        entity.setRol(user.getRol());
        entity.setIdRestaurant(user.getIdRestaurant());

        return entity;
    }

    public User toUser(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return new User(
                entity.getId(),
                entity.getName(),
                entity.getLastname(),
                entity.getIdentificationNumber(),
                entity.getNumberPhone(),
                entity.getDateOfBirth(),
                entity.getEmail(),
                entity.getClave(),
                entity.getRol(),
                entity.getIdRestaurant()
        );
    }
}
