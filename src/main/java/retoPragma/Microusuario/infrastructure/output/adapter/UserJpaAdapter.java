package retoPragma.Microusuario.infrastructure.output.adapter;

import lombok.RequiredArgsConstructor;

import retoPragma.Microusuario.domain.model.User;
import retoPragma.Microusuario.domain.util.exception.DocumentNoExistException;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.domain.spi.IUserPersistencePort;
import retoPragma.Microusuario.infrastructure.output.mapper.IUserEntityMapper;
import retoPragma.Microusuario.infrastructure.output.repository.IUserRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    @Override
    public void saveUser(User user) {
        if (userRepository.findByIdentificationNumber(user.getIdentificationNumber()).isPresent()) {
            throw new DocumentNoExistException();
        }
        userRepository.save(userEntityMapper.toUsuarioEntity(user));
    }
    @Override
    public String findRolById(Long id) {
        return userRepository.findById(id)
                .map(userEntityMapper::toUser)
                .map(user -> user.getRol().toString())
                .orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userEntityMapper::toUser)
                .orElse(null);
    }

    @Override
    public Optional<User> findByRol(RolesPlazoleta rol) {
        return userRepository.findByRol(rol)
                .map(userEntityMapper::toUser);
    }

}
