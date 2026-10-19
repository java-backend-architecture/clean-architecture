package dev.dmitriirussu.clean.architecture.infrastructure.jdbc;

import dev.dmitriirussu.clean.architecture.application.repository.UserCommandRepository;
import dev.dmitriirussu.clean.architecture.model.User;
import dev.dmitriirussu.clean.architecture.application.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;

@RequiredArgsConstructor
final class JdbcCommandRepository implements UserCommandRepository {

    private final JdbcClient jdbc;

    @Override
    public void save(User user) {
        jdbc.sql(SqlLoader.load("sql/command/insert.sql"))
                .paramSource(user)
                .update();
    }

    @Override
    public void update(User user) {
        ensureFound(
                jdbc.sql(SqlLoader.load("sql/command/update.sql"))
                .paramSource(user)
                .update()
                , user.id()
        );
    }

    @Override
    public void deleteById(String id) {
        ensureFound(
                jdbc.sql(SqlLoader.load("sql/command/delete.sql"))
                        .param("id", id)
                        .update(),
                        id
                );
    }

    private void ensureFound(int count, String id) {
        if (count != 1) throw new UserNotFoundException(id);
    }
}

