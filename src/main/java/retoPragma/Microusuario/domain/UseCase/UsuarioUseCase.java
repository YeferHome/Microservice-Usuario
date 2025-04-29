package retoPragma.Microusuario.domain.UseCase;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import retoPragma.Microusuario.domain.api.IUsuarioServicePort;
import retoPragma.Microusuario.domain.exception.*;
import retoPragma.Microusuario.domain.model.Usuario;
import retoPragma.Microusuario.domain.spi.IUsuarioPersistencePort;

import java.time.LocalDate;
import java.time.Period;

import static retoPragma.Microusuario.domain.constants.RegexConstants.CELULAR_REGEX;
import static retoPragma.Microusuario.domain.constants.RegexConstants.CORREO_REGEX;
import static retoPragma.Microusuario.domain.constants.RolesConstantes.ROLE_ADMINISTRADOR;
import static retoPragma.Microusuario.domain.constants.RolesConstantes.ROLE_PROPIETARIO;
import static retoPragma.Microusuario.domain.constants.UsuarioConstants.DOCUMENTO_POSITIVO;
import static retoPragma.Microusuario.domain.constants.UsuarioConstants.Edad_MINIMA;
import static retoPragma.Microusuario.domain.model.RolesPlazoleta.ADMINISTRADOR;
import static retoPragma.Microusuario.domain.model.RolesPlazoleta.EMPLEADO;

public class UsuarioUseCase implements IUsuarioServicePort {

    private final IUsuarioPersistencePort usuarioPersistencePort;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        if (usuario.getRol() == ADMINISTRADOR && isFirstAdmin()) {
            validarYGuardarUsuario(usuario);
            return;
        }

        Authentication auth = obtenerAutenticacion();
        String authority = obtenerAutoridad(auth);

        switch (authority) {
            case ROLE_ADMINISTRADOR:
                validarYGuardarUsuario(usuario);
                break;
            case ROLE_PROPIETARIO:
                if (usuario.getRol() == EMPLEADO) {
                    Long idPropietario = usuarioPersistencePort.findByCorreo(auth.getName()).getId();
                    crearEmpleadoPorPropietario(usuario, idPropietario);
                } else {
                    throw new NoPermissionCreateException();
                }
                break;
            default:
                throw new NoPermissionCreateException();
        }
    }

    @Override
    public String findRolById(long id) {
        return usuarioPersistencePort.findRolById(id);
    }

    @Override
    public Usuario findUsuarioByCorreo(String correo) {
        return usuarioPersistencePort.findByCorreo(correo);
    }

    @Override
    public void saveRegister(Usuario usuario) {
        validarYGuardarUsuario(usuario);
    }

    public void crearEmpleadoPorPropietario(Usuario empleado, Long idPropietario) {
        Authentication auth = obtenerAutenticacion();
        String authority = obtenerAutoridad(auth);

        if (!ROLE_PROPIETARIO.equals(authority)) {
            throw new UserNoOwnerException();
        }

        String correoAutenticado = auth.getName();
        Usuario propietario = usuarioPersistencePort.findByCorreo(correoAutenticado);

        if (propietario == null || !propietario.getId().equals(idPropietario)) {
            throw new AuthNoOwnerException();
        }

        empleado.setRol(EMPLEADO);
        validarCamposEmpleado(empleado);
        guardarUsuarioConClaveEncriptada(empleado);
    }



    private void validarYGuardarUsuario(Usuario usuario) {
        realizarValidacionesGenerales(usuario);
        guardarUsuarioConClaveEncriptada(usuario);
    }

    private void guardarUsuarioConClaveEncriptada(Usuario usuario) {
        usuario.setClave(new BCryptPasswordEncoder().encode(usuario.getClave()));
        usuarioPersistencePort.saveUsuario(usuario);
    }

    private Authentication obtenerAutenticacion() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getAuthorities().isEmpty()) {
            throw new NoAuthUserException();
        }
        return auth;
    }

    private String obtenerAutoridad(Authentication auth) {
        return auth.getAuthorities().iterator().next().getAuthority();
    }

    private boolean isFirstAdmin() {
        return usuarioPersistencePort.findByRol(ADMINISTRADOR) == null;
    }

    private void realizarValidacionesGenerales(Usuario usuario) {
        if (!esCorreoValido(usuario.getCorreo())) {
            throw new CorreoInvadedException();
        }
        if (!esCelularValido(usuario.getCelular())) {
            throw new PhoneInvalidException();
        }
        if (usuario.getDocumentoDeIdentidad() == null || usuario.getDocumentoDeIdentidad() <= DOCUMENTO_POSITIVO) {
            throw new DocumentException();
        }
        if (!esMayorDeEdad(usuario.getFechaNacimiento())) {
            throw new AgeException();
        }
    }

    private void validarCamposEmpleado(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getApellido() == null) {
            throw new FullNameException();
        }
        realizarValidacionesGenerales(usuario);
    }

    private boolean esCorreoValido(String correo) {
        return correo != null && correo.matches(CORREO_REGEX);
    }

    private boolean esCelularValido(String celular) {
        return celular != null && celular.matches(CELULAR_REGEX);
    }

    private boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        return fechaNacimiento != null && Period.between(fechaNacimiento, LocalDate.now()).getYears() >= Edad_MINIMA;
    }
}
