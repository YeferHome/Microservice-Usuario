package retoPragma.Microusuario.infrastructure.input;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retoPragma.Microusuario.application.dto.PlatoAppRequestDto;
import retoPragma.Microusuario.application.handler.IPlatoAppHandler;

@RestController
@RequestMapping("/platoApp")
@RequiredArgsConstructor
public class PlatoAppRestController {

    private final IPlatoAppHandler platoAppHandler;

    @PostMapping("/save")
    public ResponseEntity<Void> saveUsuarioInUsuarioApp(@RequestBody PlatoAppRequestDto platoAppRequestDto) {
        platoAppHandler.savePlatoInPlatoApp(platoAppRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

