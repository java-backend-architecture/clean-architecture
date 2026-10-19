package dev.dmitriirussu.clean.architecture.presentation.shared.response;

import java.time.LocalDate;

public record UserResponse(String id, String name, LocalDate birthDate) {}
