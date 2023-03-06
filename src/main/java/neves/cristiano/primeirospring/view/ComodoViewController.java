package neves.cristiano.primeirospring.view;

import lombok.RequiredArgsConstructor;
import neves.cristiano.primeirospring.controller.ComodoController;
import neves.cristiano.primeirospring.dto.ComodoRequest;
import neves.cristiano.primeirospring.model.Comodo;
import neves.cristiano.primeirospring.service.ComodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ComodoViewController {
    private final ComodoService comodoService;
    private final ComodoController comodoController;

    @RequestMapping(value = {"/comodos"})
    public String showComodoList(Model model) {
        model.addAttribute("comodos", comodoService.obterTodos());
        return "comodo-list";
    }

    @GetMapping("/comodo-new")
    public String showNovoComodo(Comodo comodo) {
        return "comodo-create";
    }

    @GetMapping("/comodo-edit/{id}")
    public String showEditComodo(@PathVariable("id") String id, Model model) {
        Comodo comodo = comodoService.obter(id);
        model.addAttribute("comodo", comodo);
        return "comodo-update";
    }

    @GetMapping("/comodo-delete/{id}")
    public String showDeleteComodo(@PathVariable("id") String id, Model model) {
        Comodo comodo = comodoService.obter(id);
        model.addAttribute("comodo", comodo);
        return "comodo-excluir";
    }

    @PostMapping("/excluir/{id}")
    public String deleteComodo(@PathVariable("id") String id) {
        comodoController.delete(id);
        return "redirect:/comodos";
    }

    @PostMapping("/comodo-add")
    public String addComodo(Comodo comodo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "comodo-create";
        }

        comodoService.criar(comodo);
        return "redirect:/comodos";
    }

    @PostMapping("/comodo-save/{id}")
    public String updateComodo(@PathVariable("id") String id, Comodo comodo,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            comodo.setId(id);
            return "comodo-update";
        }

        ComodoRequest comodoRequest = new ComodoRequest();
        comodoRequest.setNome(comodo.getNome());
        comodoRequest.setComprimento(comodo.getComprimento());
        comodoRequest.setAltura(comodo.getAltura());
        comodoRequest.setLargura(comodo.getLargura());

        comodoController.update(comodo.getId(), comodoRequest);
        return "redirect:/comodos";
    }
}
