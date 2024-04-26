package ru.gb.diplom.controllers.htmlControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.diplom.entity.Book;
import ru.gb.diplom.services.BookService;

import java.util.List;

@Controller
@RequestMapping("ui/books")
public class BookController {
    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String showAllBooks(Model model) {
        List<Book> list = service.getAllBooks();
        model.addAttribute("list", list);
        return "books";
    }
}
