package dev.dmitriirussu.clean.architecture.infrastructure.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "users")
class UserJpaEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    protected UserJpaEntity() {} // для JPA

    public UserJpaEntity(String id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String id()           { return id; }
    public String name()         { return name; }
    public LocalDate birthDate() { return birthDate; }

    public void setId(String id) { this.id = id;}
    public void setName(String name) { this.name = name;}
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate;}
}
