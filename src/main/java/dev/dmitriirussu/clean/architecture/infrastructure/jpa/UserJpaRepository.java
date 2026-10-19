package dev.dmitriirussu.clean.architecture.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface UserJpaRepository extends JpaRepository<UserJpaEntity, String> {
    List<UserJpaEntity> findAllByOrderById();
    List<UserJpaEntity> findByNameStartingWithIgnoreCaseOrderByName(String prefix);
}

