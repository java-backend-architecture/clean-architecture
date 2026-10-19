package dev.dmitriirussu.clean.architecture.presentation.rest;

import dev.dmitriirussu.clean.architecture.application.usecase.UserQueryUseCase;
import dev.dmitriirussu.clean.architecture.application.usecase.view.UserView;
import dev.dmitriirussu.clean.architecture.presentation.shared.UserWebMapper;
import dev.dmitriirussu.clean.architecture.presentation.shared.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
final class RestQueryController {

    private final UserQueryUseCase queryUseCase;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getDetails(@PathVariable String id) {

        return UserWebMapper.toResponse(queryUseCase.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> list(@RequestParam(required = false) String namePrefix) {

        List<UserView> views = (namePrefix == null)
                ? queryUseCase.findAll()
                : queryUseCase.findByNameStartingWith(namePrefix);

        return views.stream().map(UserWebMapper::toResponse).toList();
    }
}

