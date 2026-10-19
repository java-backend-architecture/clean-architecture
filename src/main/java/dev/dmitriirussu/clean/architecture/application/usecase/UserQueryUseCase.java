package dev.dmitriirussu.clean.architecture.application.usecase;

import dev.dmitriirussu.clean.architecture.application.usecase.view.UserView;

import java.util.List;

public interface UserQueryUseCase {

    UserView findById(String id);

    List<UserView> findAll();

    List<UserView> findByNameStartingWith(String prefix);
}
