package dev.dmitriirussu.clean.architecture.presentation.mvc;

import dev.dmitriirussu.clean.architecture.application.usecase.UserQueryUseCase;
import dev.dmitriirussu.clean.architecture.application.usecase.view.UserView;
import dev.dmitriirussu.clean.architecture.presentation.shared.UserWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc/users/search")
final class MvcQueryController {

    private final UserQueryUseCase queryUseCase;

    @GetMapping("/id")
    public String searchById(@RequestParam String id, Model model) {
        model.addAttribute("response", UserWebMapper.toResponse(queryUseCase.findById(id)));
        return "result/user-details";
    }

    @GetMapping("/name")
    public String searchByName(@RequestParam String name, Model model) {
        List<UserView> views = queryUseCase.findByNameStartingWith(name);
        model.addAttribute("searchTerm", name);
        model.addAttribute(
                "userViews", views.stream().map(UserWebMapper::toResponse).toList()
        );
        return "result/list";
    }
}

