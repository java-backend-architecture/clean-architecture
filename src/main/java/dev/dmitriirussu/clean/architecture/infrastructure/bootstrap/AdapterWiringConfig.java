package dev.dmitriirussu.clean.architecture.infrastructure.bootstrap;

import dev.dmitriirussu.clean.architecture.application.repository.UserCommandRepository;
import dev.dmitriirussu.clean.architecture.application.repository.UserQueryRepository;
import dev.dmitriirussu.clean.architecture.application.usecase.UserCommandUseCase;
import dev.dmitriirussu.clean.architecture.application.usecase.UserQueryUseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = {
                "dev.dmitriirussu.clean.architecture.application.service",
                AdapterWiringConfig.ADAPTER
        },
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {
                        UserCommandUseCase.class,
                        UserQueryUseCase.class,
                        UserCommandRepository.class,
                        UserQueryRepository.class
                }
        )
)
class AdapterWiringConfig {

        private static final String BASE = "dev.dmitriirussu.clean.architecture.infrastructure.";

        static final String JDBC = BASE + "jdbc";
        static final String JOOQ = BASE + "jooq";
        static final String JPA  = BASE + "jpa";

        static final String ADAPTER = JDBC;
}
