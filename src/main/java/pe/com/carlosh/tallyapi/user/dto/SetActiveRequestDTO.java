package pe.com.carlosh.tallyapi.user.dto;

import jakarta.validation.constraints.NotNull;

public record SetActiveRequestDTO(
        @NotNull Boolean active
) {}
