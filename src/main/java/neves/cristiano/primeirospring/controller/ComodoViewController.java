package neves.cristiano.primeirospring.controller;

import lombok.RequiredArgsConstructor;
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

    @RequestMapping(value = {"", "/", "/index"})
    public String showComodoList(Model model) {
        model.addAttribute("comodos", comodoService.obterTodos());
        return "index";
    }

    @GetMapping("/novo-comodo")
    public String showNovoComodo(Comodo comodo) {
        return "comodo";
    }

    @GetMapping("/edit/{id}")
    public String showEditComodo(@PathVariable("id") String id, Model model) {
        Comodo comodo = comodoService.obter(id);
        model.addAttribute("comodo", comodo);
        return "update-comodo";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteComodo(@PathVariable("id") String id, Model model) {
        Comodo comodo = comodoService.obter(id);
        model.addAttribute("comodo", comodo);
        return "excluir-comodo";
    }

    @PostMapping("/excluir/{id}")
    public String deleteComodo(@PathVariable("id") String id) {
        comodoController.delete(id);
        return "redirect:/";
    }

    @PostMapping("/addcomodo")
    public String addComodo(Comodo comodo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "comodo";
        }

        comodoService.criar(comodo);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateComodo(@PathVariable("id") String id, Comodo comodo,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            comodo.setId(id);
            return "update-comodo";
        }

        ComodoRequest comodoRequest = new ComodoRequest();
        comodoRequest.setNome(comodo.getNome());
        comodoRequest.setComprimento(comodo.getComprimento());
        comodoRequest.setAltura(comodo.getAltura());
        comodoRequest.setLargura(comodo.getLargura());

        comodoController.update(comodo.getId(), comodoRequest);
        return "redirect:/index";
    }



}
