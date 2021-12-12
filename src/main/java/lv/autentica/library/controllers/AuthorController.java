package lv.autentica.library.controllers;

import lv.autentica.library.entities.Author;
import lv.autentica.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String getAllAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "secured/authors/author_list";
    }

    @GetMapping("/{id}")
    private String viewBook(Model model, @PathVariable("id") Long id) {
        Author author = authorService.getAuthor(id);
        model.addAttribute("author", author);
        return "secured/authors/author_view";
    }

    @GetMapping("/new")
    private String newBookForm(Model model) {
        model.addAttribute("author", new Author());
        return "secured/authors/author_edit";
    }

    @PostMapping("/new")
    private String newBookSave(Model model, @ModelAttribute Author author) {
        authorService.saveAuthor(author);
        return "redirect:/authors/" + author.getId();
    }

}
