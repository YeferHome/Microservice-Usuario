package retoPragma.Microusuario.application.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import retoPragma.Microusuario.application.dto.RegisterRequestDto;
import retoPragma.Microusuario.application.dto.UserAppRequestDto;
import retoPragma.Microusuario.domain.model.User;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserAppRequestMapper {

    User toUser(UserAppRequestDto userAppRequestDto);
    User toRegister(RegisterRequestDto registerRequestDto);

}
