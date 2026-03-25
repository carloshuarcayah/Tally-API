package pe.com.carlosh.tallyapi.user.dto;

import pe.com.carlosh.tallyapi.user.Role;
import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String email,
        String username,
        String firstName,
        String lastName,
        String phone,
        Role role,
        Boolean active,
        LocalDateTime createdAt
) {}