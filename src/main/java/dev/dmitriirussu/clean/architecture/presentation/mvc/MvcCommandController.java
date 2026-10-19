package dev.dmitriirussu.clean.architecture.presentation.mvc;

import dev.dmitriirussu.clean.architecture.application.usecase.UserCommandUseCase;
import dev.dmitriirussu.clean.architecture.presentation.shared.UserWebMapper;
import dev.dmitriirussu.clean.architecture.presentation.shared.request.CreateUserRequest;
import dev.dmitriirussu.clean.architecture.presentation.shared.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc/users")
final class MvcCommandController {

    private final UserCommandUseCase commandUseCase;

    @PostMapping
    public String create(
            @ModelAttribute CreateUserRequest request,
            RedirectAttributes redirectAttributes
    ) {
        String id = commandUseCase.createUser(UserWebMapper.toCreateCommand(request));
        redirectAttributes.addFlashAttribute("message", "User information saved successfully!");
        return "redirect:/mvc/users/search/id?id=" + id;
    }

    @PostMapping("/{id}")
    public String update(
            @PathVariable String id,
            @ModelAttribute UpdateUserRequest request,
            RedirectAttributes redirectAttributes
    ) {
        commandUseCase.updateUser(UserWebMapper.toUpdateCommand(id, request));
        redirectAttributes.addFlashAttribute(
                "message", "User #" + id + " successfully updated"
        );
        return "redirect:/mvc/users/search/id?id=" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable String id,
            RedirectAttributes redirectAttributes
    ) {
        commandUseCase.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "User deleted!");
        return "redirect:/mvc/users";
    }
}

