package dev.dmitriirussu.clean.architecture.infrastructure.jooq;

import org.jooq.Field;
import org.jooq.Table;

import java.time.LocalDate;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

final class UserTable {

    static final Table<?> USERS = table("users");
    static final Field<String> ID = field("id", String.class);
    static final Field<String> NAME = field("name", String.class);
    static final Field<LocalDate> BIRTH_DATE = field("birth_date", LocalDate.class);

    private UserTable() {}
}
