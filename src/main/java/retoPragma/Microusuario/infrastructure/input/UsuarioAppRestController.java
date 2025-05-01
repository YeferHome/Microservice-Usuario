package retoPragma.Microusuario.infrastructure.input;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retoPragma.Microusuario.application.dto.UsuarioAppRequestDto;
import retoPragma.Microusuario.application.handler.IUsuarioAppHandler;

@RestController
@RequestMapping("/usuarioApp")
@RequiredArgsConstructor
public class UsuarioAppRestController {

    private final IUsuarioAppHandler usuarioAppHandler;

    @PostMapping("/saveAdmin")
    public ResponseEntity<Void> saveUsuarioInUsuarioApp(@RequestBody UsuarioAppRequestDto usuarioAppRequestDto){
        usuarioAppHandler.saveUsuarioInUsuarioApp(usuarioAppRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/saveOwner")
    public ResponseEntity<Void> savePropietarioInUsuarioApp(@RequestBody UsuarioAppRequestDto usuarioAppRequestDto){
        usuarioAppHandler.savePropietarioInUsuarioApp(usuarioAppRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/saveEmpleado")
    public ResponseEntity<Void>saveEmpleadoInUsuarioApp (@RequestBody UsuarioAppRequestDto usuarioAppRequestDto){
        usuarioAppHandler.saveEmpleadoInUsuarioApp(usuarioAppRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();}

    @GetMapping("/{id}/rol")
    String obtenerRolUsuario(@PathVariable("id") Long id){
        return usuarioAppHandler.findRolById(id);
    }

}