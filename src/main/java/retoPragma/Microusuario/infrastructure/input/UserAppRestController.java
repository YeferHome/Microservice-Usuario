package retoPragma.Microusuario.infrastructure.input;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retoPragma.Microusuario.application.dto.UserAppRequestDto;
import retoPragma.Microusuario.application.handler.IUserAppHandler;
import retoPragma.Microusuario.domain.model.User;

@RestController
@RequestMapping("/userApp")
@RequiredArgsConstructor
public class UserAppRestController {

    private final IUserAppHandler userAppHandler;

    @Operation(summary = "Crear Administrador en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador creado con exito",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                    }),
            @ApiResponse(responseCode = "500", description = "Error de parametros", content = @Content),
            @ApiResponse(responseCode = "400", description = "Error de response", content = @Content)

    })
    @PostMapping("/saveAdmin")
    public ResponseEntity<Void> saveUserInUserApp(@RequestBody UserAppRequestDto userAppRequestDto){
        userAppHandler.saveUserInUserApp(userAppRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Crear Propietario en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Propietario creado con exito",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                    }),
            @ApiResponse(responseCode = "500", description = "Error de parametros", content = @Content),
            @ApiResponse(responseCode = "400", description = "Error de response", content = @Content)

    })

    @PostMapping("/saveOwner")
    public ResponseEntity<Void> saveOwnerInUserApp(@RequestBody UserAppRequestDto userAppRequestDto){
        userAppHandler.saveOwnerInUserApp(userAppRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Crear Empleado en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado creado con exito",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                    }),
            @ApiResponse(responseCode = "500", description = "Error de parametros", content = @Content),
            @ApiResponse(responseCode = "400", description = "Error de response", content = @Content)

    })
    @PostMapping("/saveEmployee")
    public ResponseEntity<Void> saveEmployeeInUserApp(@RequestBody UserAppRequestDto userAppRequestDto){
        userAppHandler.saveEmployeeInUserApp(userAppRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/rol")
    String obtainRolUser(@PathVariable("id") Long id){
        return userAppHandler.findRolById(id);
    }

}