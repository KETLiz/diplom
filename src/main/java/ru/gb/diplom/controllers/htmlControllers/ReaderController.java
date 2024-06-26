package ru.gb.diplom.controllers.htmlControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.diplom.entity.Issue;
import ru.gb.diplom.entity.Reader;
import ru.gb.diplom.services.BookService;
import ru.gb.diplom.services.IssueService;
import ru.gb.diplom.services.ReaderService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("ui/reader")
public class ReaderController {
    private final ReaderService readerService;
    private IssueService issueService;
    private BookService bookService;
    public ReaderController(ReaderService readerService, IssueService issueService, BookService bookService) {
        this.readerService = readerService;
        this.issueService = issueService;
        this.bookService = bookService;
    }
    @GetMapping
    public String getAllReaders(Model model) {
        List<Reader> list = readerService.getAllReaders();
        model.addAttribute("readerList", list);
        return "readers";
    }

    @GetMapping("{id}")
    public String bookNameReaderHaveNow(@PathVariable("id") long readerId, Model model) {
        List<Issue> searchIssues = issueService.allIssuesByReaderIdBookOnHands(readerId);
        String readerName = readerService.getReaderNameById(readerId);
        List<String> booksName = new ArrayList<>();
        for(Issue i : searchIssues) {
            long bookId = i.getIdBook();
            booksName.add(bookService.getBookNameById(bookId));
        }
        model.addAttribute("readerId", readerId);
        model.addAttribute("readerName", readerName);
        model.addAttribute("books", booksName);
        return "reader";

    }
}
