package retoPragma.Microusuario.application.handler;


import retoPragma.Microusuario.application.dto.UserAppRequestDto;

public interface IUserAppHandler {
    void saveUserInUserApp(UserAppRequestDto userAppRequestDto);
    void saveOwnerInUserApp(UserAppRequestDto userAppRequestDto);
    void saveEmployeeInUserApp(UserAppRequestDto userAppRequestDto);
    String findRolById(Long id);

}
