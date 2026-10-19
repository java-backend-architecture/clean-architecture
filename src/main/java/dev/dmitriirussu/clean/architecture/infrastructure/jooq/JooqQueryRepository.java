package dev.dmitriirussu.clean.architecture.infrastructure.jooq;

import static dev.dmitriirussu.clean.architecture.infrastructure.jooq.UserTable.*;
import dev.dmitriirussu.clean.architecture.application.repository.UserQueryRepository;
import dev.dmitriirussu.clean.architecture.application.usecase.view.UserView;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
final class JooqQueryRepository implements UserQueryRepository {

    private final DSLContext dsl;

    @Override
    public Optional<UserView> findById(String id) {
        return dsl.select(ID, NAME, BIRTH_DATE)
                .from(USERS)
                .where(ID.eq(id))
                .fetchOptionalInto(UserView.class);
    }

    @Override
    public List<UserView> findAll() {
        return dsl.select(ID, NAME, BIRTH_DATE)
                .from(USERS)
                .orderBy(ID)
                .fetchInto(UserView.class);
    }

    @Override
    public List<UserView> findByNameStartingWith(String prefix) {
        return dsl.select(ID, NAME, BIRTH_DATE)
                .from(USERS)
                .where(NAME.startsWithIgnoreCase(prefix))
                .orderBy(NAME)
                .fetchInto(UserView.class);
    }
}