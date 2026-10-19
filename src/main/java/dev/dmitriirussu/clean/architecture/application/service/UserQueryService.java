package dev.dmitriirussu.clean.architecture.application.service;

import dev.dmitriirussu.clean.architecture.application.repository.UserQueryRepository;
import dev.dmitriirussu.clean.architecture.application.usecase.UserQueryUseCase;
import dev.dmitriirussu.clean.architecture.application.usecase.view.UserView;
import dev.dmitriirussu.clean.architecture.application.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
final class UserQueryService implements UserQueryUseCase {

    private final UserQueryRepository users;

    @Override
    public UserView findById(String id) {
        return users.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
    }

    @Override
    public List<UserView> findAll() {
        return users.findAll();
    }

    @Override
    public List<UserView> findByNameStartingWith(String prefix) {
        return users.findByNameStartingWith(prefix);
    }
}

