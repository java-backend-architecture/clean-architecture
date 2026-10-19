package dev.dmitriirussu.clean.architecture.infrastructure.jpa;

import dev.dmitriirussu.clean.architecture.application.repository.UserQueryRepository;
import dev.dmitriirussu.clean.architecture.application.usecase.view.UserView;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
final class JpaQueryRepository implements UserQueryRepository {

    private final UserJpaRepository jpa;

    @Override
    public Optional<UserView> findById(String id) {
        return jpa.findById(id).map(UserJpaMapper::toView);
    }

    @Override
    public List<UserView> findAll() {
        return jpa.findAllByOrderById()
                .stream()
                .map(UserJpaMapper::toView)
                .toList();
    }

    @Override
    public List<UserView> findByNameStartingWith(String prefix) {
        return jpa.findByNameStartingWithIgnoreCaseOrderByName(prefix)
                .stream()
                .map(UserJpaMapper::toView)
                .toList();
    }
}
