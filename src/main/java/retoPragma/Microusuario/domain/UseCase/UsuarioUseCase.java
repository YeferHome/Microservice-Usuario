package retoPragma.Microusuario.domain.UseCase;


import retoPragma.Microusuario.domain.api.IUsuarioServicePort;
import retoPragma.Microusuario.domain.exception.*;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.domain.model.Usuario;
import retoPragma.Microusuario.domain.spi.ISecurityServicePort;
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
    private final ISecurityServicePort securityServicePort;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort,
                          ISecurityServicePort securityServicePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.securityServicePort = securityServicePort;
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        if (usuario.getRol() == ADMINISTRADOR && isFirstAdmin()) {
            validarYGuardarUsuario(usuario);
            return;
        }

        String authority = securityServicePort.getAuthenticatedAuthority();

        switch (authority) {
            case ROLE_ADMINISTRADOR:
                validarYGuardarUsuario(usuario);
                break;
            case ROLE_PROPIETARIO:
                if (usuario.getRol() == EMPLEADO) {
                    Long idPropietario = usuarioPersistencePort.findByCorreo(securityServicePort.getAuthenticatedUsername()).getId();
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
        if (usuario.getRol() != RolesPlazoleta.CLIENTE) {
            throw new NoPermissionCreateException();
        }
        validarYGuardarUsuario(usuario);
    }

    public void crearEmpleadoPorPropietario(Usuario empleado, Long idPropietario) {
        String authority = securityServicePort.getAuthenticatedAuthority();

        if (!ROLE_PROPIETARIO.equals(authority)) {
            throw new UserNoOwnerException();
        }

        String correoAutenticado = securityServicePort.getAuthenticatedUsername();
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
        usuario.setClave(securityServicePort.encodePassword(usuario.getClave()));
        usuarioPersistencePort.saveUsuario(usuario);
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