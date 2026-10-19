package dev.dmitriirussu.clean.architecture.application.repository;

import dev.dmitriirussu.clean.architecture.model.User;

public interface UserCommandRepository {

    void save(User user);

    void update(User user);

    void deleteById(String id);
}
