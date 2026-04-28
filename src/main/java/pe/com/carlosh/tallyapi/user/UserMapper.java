package pe.com.carlosh.tallyapi.user;

import pe.com.carlosh.tallyapi.user.dto.UserRequestDTO;
import pe.com.carlosh.tallyapi.user.dto.UserResponseDTO;

public class UserMapper {


    public static User toEntity(UserRequestDTO req, String encodedPassword) {
        return new User(
                req.email(),
                req.name(),
                encodedPassword
        );
    }

    public static UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole(),
                user.isEnabled(),
                user.getCreatedAt()
        );
    }
}
