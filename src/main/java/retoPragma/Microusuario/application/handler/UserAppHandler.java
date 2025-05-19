package retoPragma.Microusuario.application.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import retoPragma.Microusuario.application.dto.UserAppRequestDto;
import retoPragma.Microusuario.application.mapper.IUserAppRequestMapper;
import retoPragma.Microusuario.domain.api.IUserServicePort;
import retoPragma.Microusuario.domain.model.User;

@Service
@RequiredArgsConstructor
public class UserAppHandler implements IUserAppHandler {

    private final IUserServicePort userServicePort;
    private final IUserAppRequestMapper userAppRequestMapper;

    @Override
    public void saveUserInUserApp(UserAppRequestDto userAppRequestDto) {
        User user = userAppRequestMapper.toUser(userAppRequestDto);
        userServicePort.saveUser(user);
    }

    @Override
    public void saveOwnerInUserApp(UserAppRequestDto userAppRequestDto){
        User owner =  userAppRequestMapper.toUser(userAppRequestDto);
        userServicePort.saveUser(owner);
    }

    @Override
    public void saveEmployeeInUserApp(UserAppRequestDto userAppRequestDto){
        User employee =  userAppRequestMapper.toUser(userAppRequestDto);
        userServicePort.saveUser(employee);
    }


    @Override
    public String findRolById(Long id) {
        return userServicePort.findRolById(id);
    }


}

