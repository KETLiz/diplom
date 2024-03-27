package ru.gb.springbootlesson3.controllers.htmlControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.services.IssueService;

import java.util.List;

@Controller
@RequestMapping("ui/issues")
public class IssueController {
    private final IssueService service;
    public IssueController(IssueService service) {
        this.service = service;
    }
    @GetMapping
    public String allIssues(Model model) {
        List<Issue> issuesList = service.getAllIssues();
        model.addAttribute("issuesList", issuesList);
        return "issues";
    }
}
