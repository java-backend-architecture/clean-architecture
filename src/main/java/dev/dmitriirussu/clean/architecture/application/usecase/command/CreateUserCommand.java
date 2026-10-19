package dev.dmitriirussu.clean.architecture.application.usecase.command;

import java.time.LocalDate;

public record CreateUserCommand(String name, LocalDate birthDate) {}

