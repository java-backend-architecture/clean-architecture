package dev.dmitriirussu.clean.architecture.model;

import java.time.LocalDate;

public record User(String id, String name, LocalDate birthDate) {
    public User {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name is blank");
        if (birthDate == null) throw new IllegalArgumentException("birthDate is null");
    }
}
