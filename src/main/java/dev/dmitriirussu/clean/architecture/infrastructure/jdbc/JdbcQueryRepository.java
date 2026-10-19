package dev.dmitriirussu.clean.architecture.infrastructure.jdbc;

import dev.dmitriirussu.clean.architecture.application.repository.UserQueryRepository;
import dev.dmitriirussu.clean.architecture.application.usecase.view.UserView;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
final class JdbcQueryRepository implements UserQueryRepository {

    private final JdbcClient jdbc;

    @Override
    public Optional<UserView> findById(String id) {
        return jdbc.sql(SqlLoader.load("sql/query/select-by-id.sql"))
                .param("id", id)
                .query(UserView.class)
                .optional();
    }

    @Override
    public List<UserView> findAll() {
        return jdbc.sql(SqlLoader.load("sql/query/select-all.sql"))
                .query(UserView.class)
                .list();
    }

    @Override
    public List<UserView> findByNameStartingWith(String prefix) {
        return jdbc.sql(SqlLoader.load("sql/query/select-by-name-prefix.sql"))
                .param("prefix", prefix)
                .query(UserView.class)
                .list();
    }
}

