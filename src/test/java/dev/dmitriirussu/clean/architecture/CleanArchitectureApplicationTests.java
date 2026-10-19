package dev.dmitriirussu.clean.architecture;

import dev.dmitriirussu.clean.architecture.application.repository.UserCommandRepository;
import dev.dmitriirussu.clean.architecture.application.repository.UserQueryRepository;
import dev.dmitriirussu.clean.architecture.application.usecase.UserCommandUseCase;
import dev.dmitriirussu.clean.architecture.application.usecase.UserQueryUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CleanArchitectureApplicationTests {

	@Autowired
	ApplicationContext ctx;

	@Test
	void contextLoads() {
	}

	@Test
	void portsAreWired() {
		assertThat(ctx.getBeansOfType(UserQueryRepository.class)).hasSize(1);
		assertThat(ctx.getBeansOfType(UserCommandRepository.class)).hasSize(1);
		assertThat(ctx.getBeansOfType(UserCommandUseCase.class)).hasSize(1);
		assertThat(ctx.getBeansOfType(UserQueryUseCase.class)).hasSize(1);
	}

	@Test
	void useCasesAreTransactionalProxies() {
		assertThat(AopUtils.isJdkDynamicProxy(ctx.getBean(UserCommandUseCase.class))).isTrue();
		assertThat(AopUtils.isJdkDynamicProxy(ctx.getBean(UserQueryUseCase.class))).isTrue();
	}
}
