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

    @PostMapping("/save")
    public ResponseEntity<Void> saveUsuarioInUsuarioApp(@RequestBody UsuarioAppRequestDto usuarioAppRequestDto){
        usuarioAppHandler.saveUsuarioInUsuarioApp(usuarioAppRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @GetMapping("/{id}/rol")
    String obtenerRolUsuario(@PathVariable("id") Long id){
        return usuarioAppHandler.findRolById(id);
    }

}