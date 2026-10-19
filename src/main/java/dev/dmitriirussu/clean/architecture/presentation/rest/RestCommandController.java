package dev.dmitriirussu.clean.architecture.presentation.rest;

import dev.dmitriirussu.clean.architecture.application.usecase.UserCommandUseCase;
import dev.dmitriirussu.clean.architecture.presentation.shared.UserWebMapper;
import dev.dmitriirussu.clean.architecture.presentation.shared.request.CreateUserRequest;
import dev.dmitriirussu.clean.architecture.presentation.shared.request.UpdateUserRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
final class RestCommandController {

    private final UserCommandUseCase commandUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @RequestBody CreateUserRequest request,
            HttpServletResponse response
    ) {
        String id = commandUseCase.createUser(UserWebMapper.toCreateCommand(request));
        response.setHeader(HttpHeaders.LOCATION, "/api/users/" + id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable String id,
            @RequestBody UpdateUserRequest request
    ) {
        commandUseCase.updateUser(UserWebMapper.toUpdateCommand(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        commandUseCase.deleteUser(id);
    }
}
