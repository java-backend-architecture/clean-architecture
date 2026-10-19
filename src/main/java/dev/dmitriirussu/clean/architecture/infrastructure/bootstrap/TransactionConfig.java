package dev.dmitriirussu.clean.architecture.infrastructure.bootstrap;

import dev.dmitriirussu.clean.architecture.application.usecase.UserCommandUseCase;
import dev.dmitriirussu.clean.architecture.application.usecase.UserQueryUseCase;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.aop.Advisor;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.aop.support.ComposablePointcut;

@Configuration
class TransactionConfig {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    static Advisor commandTransactions(TransactionManager tm) {
        return advisor(UserCommandUseCase.class, tm, false);
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    static Advisor queryTransactions(TransactionManager tm) {
        return advisor(UserQueryUseCase.class, tm, true);
    }

    private static Advisor advisor(Class<?> type, TransactionManager tm, boolean readOnly) {

        var attribute = new DefaultTransactionAttribute();
        attribute.setReadOnly(readOnly);

        var source = new MatchAlwaysTransactionAttributeSource();
        source.setTransactionAttribute(attribute);

        return new DefaultPointcutAdvisor(
                new ComposablePointcut((ClassFilter) type::isAssignableFrom),
                new TransactionInterceptor(tm, source));
    }
}