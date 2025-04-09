package retoPragma.Microusuario.domain.UseCase;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import retoPragma.Microusuario.domain.api.IUsuarioServicePort;
import retoPragma.Microusuario.domain.model.Usuario;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.domain.spi.IUsuarioPersistencePort;
import retoPragma.Microusuario.infrastructure.exception.BusinessException;

import java.time.LocalDate;
import java.time.Period;

public class UsuarioUseCase implements IUsuarioServicePort {

    private final IUsuarioPersistencePort usuarioPersistencePort;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
    }

    @Override
    public void saveUsuario(Usuario usuario) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getAuthorities().isEmpty()) {
            throw new BusinessException("No estás autenticado, no se puede crear un usuario.");
        }

        String authority = auth.getAuthorities().iterator().next().getAuthority();

        if ("ROLE_ADMINISTRADOR".equals(authority)) {
            realizarValidacionesGenerales(usuario);
            usuario.setClave(new BCryptPasswordEncoder().encode(usuario.getClave()));
            usuarioPersistencePort.saveUsuario(usuario);
            return;
        }

        if ("ROLE_PROPIETARIO".equals(authority) && usuario.getRol() == RolesPlazoleta.EMPLEADO) {
            Long idPropietario = usuarioPersistencePort.findByCorreo(auth.getName()).getId();
            crearEmpleadoPorPropietario(usuario, idPropietario);
            return;
        }

        throw new BusinessException("No tienes permisos para crear este tipo de usuario.");
    }

    @Override
    public String findRolById(long id) {
        return usuarioPersistencePort.findRolById(id);
    }

    @Override
    public Usuario findUsuarioByCorreo(String correo) {
        return usuarioPersistencePort.findByCorreo(correo);
    }


    public void crearEmpleadoPorPropietario(Usuario empleado, Long idPropietario) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getAuthorities().isEmpty()) {
            throw new BusinessException("Debes estar autenticado para crear empleados.");
        }


        String authority = auth.getAuthorities().iterator().next().getAuthority();

        if (!"ROLE_PROPIETARIO".equals(authority)) {
            throw new BusinessException("Solo un propietario puede crear empleados.");
        }

        String correoAutenticado = auth.getName();
        Usuario propietario = usuarioPersistencePort.findByCorreo(correoAutenticado);
        if (propietario == null || !propietario.getId().equals(idPropietario)) {
            throw new BusinessException("El usuario autenticado no coincide con el propietario indicado.");
        }


        empleado.setRol(RolesPlazoleta.EMPLEADO);

        validarCamposEmpleado(empleado);

        empleado.setClave(new BCryptPasswordEncoder().encode(empleado.getClave()));

        usuarioPersistencePort.saveUsuario(empleado);
    }

    private void realizarValidacionesGenerales(Usuario usuario) {
        if (!esCorreoValido(usuario.getCorreo())) {
            throw new BusinessException("Correo no válido, revise la estructura (ej. usuario@dominio.com)");
        }

        if (!esCelularValido(usuario.getCelular())) {
            throw new BusinessException("Teléfono inválido; máximo 13 dígitos y debe iniciar con '+'.");
        }

        if (usuario.getDocumentoDeIdentidad() == null || usuario.getDocumentoDeIdentidad() <= 0) {
            throw new BusinessException("Documento de identidad debe ser un número positivo.");
        }

        if (!esMayorDeEdad(usuario.getFechaNacimiento())) {
            throw new BusinessException("El usuario debe ser mayor de 18 años.");
        }
    }

    private void validarCamposEmpleado(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getApellido() == null) {
            throw new BusinessException("Nombre y Apellido obligatorios.");
        }
        realizarValidacionesGenerales(usuario);
    }

    private boolean esCorreoValido(String correo) {
        if (correo == null) return false;
        return correo.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    private boolean esCelularValido(String celular) {
        if (celular == null) return false;

        return celular.matches("^\\+[1-9]{1,3}\\d{10}$");
    }

    private boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) return false;

        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }
}
