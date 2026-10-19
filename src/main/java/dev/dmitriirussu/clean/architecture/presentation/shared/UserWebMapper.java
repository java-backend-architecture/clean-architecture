package dev.dmitriirussu.clean.architecture.presentation.shared;

import dev.dmitriirussu.clean.architecture.application.usecase.command.CreateUserCommand;
import dev.dmitriirussu.clean.architecture.application.usecase.command.UpdateUserCommand;
import dev.dmitriirussu.clean.architecture.application.usecase.view.UserView;
import dev.dmitriirussu.clean.architecture.presentation.shared.request.CreateUserRequest;
import dev.dmitriirussu.clean.architecture.presentation.shared.request.UpdateUserRequest;
import dev.dmitriirussu.clean.architecture.presentation.shared.response.UserResponse;

import java.time.LocalDate;

public final class UserWebMapper {

    private UserWebMapper() {}

    public static CreateUserCommand toCreateCommand(CreateUserRequest request) {
        return new CreateUserCommand(request.name(), LocalDate.parse(request.birthDate()));
    }

    public static UpdateUserCommand toUpdateCommand(String id, UpdateUserRequest request) {
        return new UpdateUserCommand(id, request.name(), LocalDate.parse(request.birthDate()));
    }

    public static UserResponse toResponse(UserView view) {
        return new UserResponse(view.id(), view.name(), view.birthDate());
    }
}