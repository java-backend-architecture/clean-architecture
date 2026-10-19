package dev.dmitriirussu.clean.architecture.application.usecase.view;

import java.time.LocalDate;

public record UserView(String id, String name, LocalDate birthDate) {}
