package dev.dmitriirussu.clean.architecture.application.repository;

import dev.dmitriirussu.clean.architecture.application.usecase.view.UserView;

import java.util.List;
import java.util.Optional;

public interface UserQueryRepository {

    Optional<UserView> findById(String id);

    List<UserView> findAll();

    List<UserView> findByNameStartingWith(String prefix);
}

