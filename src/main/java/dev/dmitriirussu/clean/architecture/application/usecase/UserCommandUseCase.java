package dev.dmitriirussu.clean.architecture.application.usecase;

import dev.dmitriirussu.clean.architecture.application.usecase.command.CreateUserCommand;
import dev.dmitriirussu.clean.architecture.application.usecase.command.UpdateUserCommand;

public interface UserCommandUseCase {

    String createUser(CreateUserCommand command);

    void updateUser(UpdateUserCommand command);

    void deleteUser(String id);
}

