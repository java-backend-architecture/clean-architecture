package dev.dmitriirussu.clean.architecture.application.usecase.command;

import java.time.LocalDate;

public record UpdateUserCommand(String id, String name, LocalDate birthDate) {}
