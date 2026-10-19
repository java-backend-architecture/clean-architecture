package dev.dmitriirussu.clean.architecture.infrastructure.jpa;

import dev.dmitriirussu.clean.architecture.application.repository.UserCommandRepository;
import dev.dmitriirussu.clean.architecture.model.User;
import dev.dmitriirussu.clean.architecture.application.UserNotFoundException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
final class JpaCommandRepository implements UserCommandRepository {

    private final EntityManager em;

    @Override
    public void save(User user) {
        em.persist(new UserJpaEntity(user.id(), user.name(), user.birthDate()));
    }

    @Override
    public void update(User user) {
        UserJpaEntity entity = em.find(UserJpaEntity.class, user.id());
        if (entity == null) throw new UserNotFoundException(user.id());
        entity.setName(user.name());
        entity.setBirthDate(user.birthDate());
    }

    @Override
    public void deleteById(String id) {
        UserJpaEntity entity = em.find(UserJpaEntity.class, id);
        if (entity == null) throw new UserNotFoundException(id);
        em.remove(entity);
    }
}
