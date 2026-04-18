package pe.com.carlosh.tallyapi.user.dto;

import jakarta.validation.constraints.NotNull;
import pe.com.carlosh.tallyapi.user.Role;

public record ChangeRoleRequestDTO(
        @NotNull Role role
) {}
