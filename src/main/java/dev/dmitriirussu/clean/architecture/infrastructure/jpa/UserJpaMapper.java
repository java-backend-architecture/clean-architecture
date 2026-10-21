package dev.dmitriirussu.clean.architecture.infrastructure.jpa;

import dev.dmitriirussu.clean.architecture.application.usecase.view.UserView;
import dev.dmitriirussu.clean.architecture.model.User;

final class UserJpaMapper {

    private UserJpaMapper() {}

    static UserJpaEntity toEntity(User user) {
        return new UserJpaEntity(user.id(), user.name(), user.birthDate());
    }

    static UserView toView(UserJpaEntity e) {
        return new UserView(e.id(), e.name(), e.birthDate());
    }
}

