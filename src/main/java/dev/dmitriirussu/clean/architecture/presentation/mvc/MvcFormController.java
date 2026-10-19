package dev.dmitriirussu.clean.architecture.presentation.mvc;

import dev.dmitriirussu.clean.architecture.application.usecase.UserQueryUseCase;
import dev.dmitriirussu.clean.architecture.application.usecase.view.UserView;
import dev.dmitriirussu.clean.architecture.presentation.shared.UserWebMapper;
import dev.dmitriirussu.clean.architecture.presentation.shared.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc/users")
final class MvcFormController {

    private final UserQueryUseCase queryUseCase;

    @GetMapping
    public String showSearchForm() {
        return "form/main-page-search";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("request", new CreateUserRequest("", ""));
        return "form/create-form";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(Model model, @PathVariable String id) {
        model.addAttribute("response", UserWebMapper.toResponse(queryUseCase.findById(id)));
        return "form/edit-form";
    }

    @GetMapping("/{id}/delete")
    public String showDeleteConfirmationForm(@PathVariable String id, Model model) {
        UserView view = queryUseCase.findById(id);
        model.addAttribute("response", UserWebMapper.toResponse(view));
        return "form/delete-confirm-form";
    }
}

