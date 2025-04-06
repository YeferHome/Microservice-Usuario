package retoPragma.Microusuario.domain.UseCase;



import retoPragma.Microusuario.domain.api.IUsuarioServicePort;
import retoPragma.Microusuario.domain.model.RolesPlazoleta;
import retoPragma.Microusuario.domain.model.Usuario;
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
    public void savePropietario(Usuario usuario) {

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

        usuario.setRol(RolesPlazoleta.PROPIETARIO);
        usuarioPersistencePort.savePropietario(usuario);
    }



    private boolean esCorreoValido(String correo) {
        if (correo == null) return false;
        return correo.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    private boolean esCelularValido(String celular) {
        if (celular == null) return false;

        return celular.matches("\\+?\\d{1,13}");
    }

    private boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) return false;

        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }
}