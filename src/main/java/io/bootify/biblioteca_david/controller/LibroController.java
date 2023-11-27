package io.bootify.biblioteca_david.controller;

import io.bootify.biblioteca_david.model.EstadoLibro;
import io.bootify.biblioteca_david.model.LibroDTO;
import io.bootify.biblioteca_david.service.LibroService;
import io.bootify.biblioteca_david.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(final LibroService libroService) {
        this.libroService = libroService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("estadoValues", EstadoLibro.values());
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("libroes", libroService.findAll());
        return "libro/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("libro") final LibroDTO libroDTO) {
        return "libro/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("libro") @Valid final LibroDTO libroDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "libro/add";
        }
        libroService.create(libroDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("libro.create.success"));
        return "redirect:/libros";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("libro", libroService.get(id));
        return "libro/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("libro") @Valid final LibroDTO libroDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "libro/edit";
        }
        libroService.update(id, libroDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("libro.update.success"));
        return "redirect:/libros";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = libroService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            libroService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("libro.delete.success"));
        }
        return "redirect:/libros";
    }

}
