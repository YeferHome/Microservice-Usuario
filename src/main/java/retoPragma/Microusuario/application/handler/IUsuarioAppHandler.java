package retoPragma.Microusuario.application.handler;


import retoPragma.Microusuario.application.dto.UsuarioAppRequestDto;

public interface IUsuarioAppHandler {
    void saveUsuarioInUsuarioApp(UsuarioAppRequestDto usuarioAppRequestDto);
    void savePropietarioInUsuarioApp(UsuarioAppRequestDto usuarioAppRequestDto);
    void saveEmpleadoInUsuarioApp(UsuarioAppRequestDto usuarioAppRequestDto);
    String findRolById(Long id);

}
