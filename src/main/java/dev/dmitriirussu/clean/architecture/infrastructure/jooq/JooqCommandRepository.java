package dev.dmitriirussu.clean.architecture.infrastructure.jooq;

import static dev.dmitriirussu.clean.architecture.infrastructure.jooq.UserTable.*;
import dev.dmitriirussu.clean.architecture.application.repository.UserCommandRepository;
import dev.dmitriirussu.clean.architecture.model.User;
import dev.dmitriirussu.clean.architecture.application.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

@RequiredArgsConstructor
final class JooqCommandRepository implements UserCommandRepository {

    private final DSLContext dsl;

    @Override
    public void save(User user) {
        dsl.insertInto(USERS)
                .set(ID, user.id())
                .set(NAME, user.name())
                .set(BIRTH_DATE, user.birthDate())
                .execute();
    }

    @Override
    public void update(User user) {
        int updated = dsl.update(USERS)
                .set(NAME, user.name())
                .set(BIRTH_DATE, user.birthDate())
                .where(ID.eq(user.id()))
                .execute();
        ensureFound(updated, user.id());
    }

    @Override
    public void deleteById(String id) {
        int deleted = dsl.deleteFrom(USERS)
                .where(ID.eq(id))
                .execute();
        ensureFound(deleted, id);
    }

    private void ensureFound(int count, String id) {
        if (count != 1) throw new UserNotFoundException(id);
    }
}