package lv.autentica.library.controllers;

import lv.autentica.library.entities.Book;
import lv.autentica.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }

    @GetMapping("books/{id}")
    private String viewBook(Model model, @PathVariable("id") Long id) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "book_view";
    }

    @GetMapping("books/new")
    private String newBook(Model model, @ModelAttribute("book") Book book) {
        model.addAttribute("book", book);
        return "secured/book_edit";
    }

}
