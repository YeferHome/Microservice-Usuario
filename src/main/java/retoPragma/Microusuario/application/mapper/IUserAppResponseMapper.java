package retoPragma.Microusuario.application.mapper;


import org.mapstruct.*;
import retoPragma.Microusuario.application.dto.UserResponseDto;
import retoPragma.Microusuario.domain.model.User;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserAppResponseMapper {

    UserResponseDto toUserResponseDto(User user);
}
  