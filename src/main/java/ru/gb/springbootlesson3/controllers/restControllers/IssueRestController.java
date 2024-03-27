package ru.gb.springbootlesson3.controllers.restControllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.services.IssueService;

import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("issue")
//@RequiredArgsConstructor
public class IssueRestController {

    @Autowired
    private IssueService service;

    public IssueRestController(IssueService service) {
        this.service = service;
    }

    @GetMapping
    public List<Issue> getAllIssues() {
        return service.getAllIssues();
    }

    @GetMapping("{id}")
    public Issue getIssueById(@PathVariable long id) {
        return service.getIssueById(id);
    }

    @PostMapping
    public ResponseEntity<Issue> createNewIssue(@RequestBody IssueRequest request) {
        try {
            if(service.createNewIssue(request) != null) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
        }
    }


    @PutMapping("{issueId}")
    public Issue returnBook(@PathVariable long issueId) {
        Issue searchIssue = service.returnBook(issueId);


        return searchIssue;
    }
}
