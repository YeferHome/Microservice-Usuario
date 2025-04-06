package retoPragma.Microusuario.infrastructure.input;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retoPragma.Microusuario.application.dto.UsuarioAppRequestDto;
import retoPragma.Microusuario.application.handler.IUsuarioAppHandler;

@RestController
@RequestMapping("/usuarioApp")
@RequiredArgsConstructor
public class UsuarioAppRestController {

    private final IUsuarioAppHandler usuarioAppHandler;

    @PostMapping("/save")
    public ResponseEntity<?> saveUsuarioInUsuarioApp(@RequestBody UsuarioAppRequestDto usuarioAppRequestDto){
        usuarioAppHandler.saveUsuarioInUsuarioApp(usuarioAppRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}