package neves.cristiano.primeirospring.view;

import lombok.RequiredArgsConstructor;
import neves.cristiano.primeirospring.controller.ComodoController;
import neves.cristiano.primeirospring.dto.ComodoRequest;
import neves.cristiano.primeirospring.dto.ComodoResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ComodoViewController {
    private final ComodoController comodoController;

    @RequestMapping(value = {"/comodos"})
    public String showComodoList(Model model) {
        model.addAttribute("comodos", comodoController.readAll());
        return "comodo-list";
    }

    @GetMapping("/comodo-new")
    public String showNovoComodo(Model model, ComodoResponse comodoResponse) {
        model.addAttribute("comodo", comodoResponse);
        return "comodo-create";
    }

    @GetMapping("/comodo-edit/{id}")
    public String showEditComodo(@PathVariable("id") String id, Model model) {
        ComodoResponse comodoResponse = comodoController.read(id);
        model.addAttribute("comodo", comodoResponse);
        return "comodo-update";
    }

    @GetMapping("/comodo-delete/{id}")
    public String showDeleteComodo(@PathVariable("id") String id, Model model) {
        ComodoResponse comodoResponse = comodoController.read(id);
        model.addAttribute("comodo", comodoResponse);
        return "comodo-excluir";
    }

    @PostMapping("/excluir/{id}")
    public String deleteComodo(@PathVariable("id") String id) {
        comodoController.delete(id);
        return "redirect:/comodos";
    }

    @PostMapping("/comodo-add")
    public String addComodo(ComodoResponse comodo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "comodo-create";
        }

        ComodoRequest comodoRequest = new ComodoRequest();
        BeanUtils.copyProperties(comodo, comodoRequest);
        comodoController.create(comodoRequest);
        return "redirect:/comodos";
    }

    @PostMapping("/comodo-save/{id}")
    public String updateComodo(@PathVariable("id") String id, ComodoResponse comodo,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            comodo.setId(id);
            return "comodo-update";
        }

        ComodoRequest comodoRequest = new ComodoRequest();
        BeanUtils.copyProperties(comodo, comodoRequest);

        comodoController.update(comodo.getId(), comodoRequest);
        return "redirect:/comodos";
    }
}
