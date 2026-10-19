package dev.dmitriirussu.clean.architecture.application.service;

import dev.dmitriirussu.clean.architecture.application.repository.UserCommandRepository;
import dev.dmitriirussu.clean.architecture.application.usecase.UserCommandUseCase;
import dev.dmitriirussu.clean.architecture.application.usecase.command.CreateUserCommand;
import dev.dmitriirussu.clean.architecture.application.usecase.command.UpdateUserCommand;
import dev.dmitriirussu.clean.architecture.model.User;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
final class UserCommandService implements UserCommandUseCase {

    private final UserCommandRepository repository;

    @Override
    public String createUser(CreateUserCommand command) {
        String id = UUID.randomUUID().toString();
        repository.save(new User(id, command.name(), command.birthDate()));
        return id;
    }

    @Override
    public void updateUser(UpdateUserCommand command) {
        repository.update(new User(command.id(), command.name(), command.birthDate()));
    }

    @Override
    public void deleteUser(String id) {
        repository.deleteById(id);
    }
}
