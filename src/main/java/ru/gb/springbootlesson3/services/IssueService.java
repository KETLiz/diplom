package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.controllers.restControllers.IssueRequest;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.repository.BookRepository;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
    @Value("${application.issue.max-allowed-books}")
    private int maxBookCount;

    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue getIssueById(long id) {
        return issueRepository.findById(id).orElseThrow();
    }

    public Issue createNewIssue(IssueRequest request) {
        if(readerHaveBooksLessThanThree(request)) {
            return issueRepository.save(new Issue(request.getReaderId(), request.getBookId()));
        }
        return null;
    }

    public boolean readerHaveBooksLessThanThree(IssueRequest request) {
        long readerIdFromRequest = request.getReaderId();
        List<Issue> issues = getAllIssues();
        List<Issue> issuesByReaderFromRequest = new ArrayList<>();
        for(Issue i : issues) {
            if(i.getIdReader() == readerIdFromRequest && i.getReturnedAt() == null) {
                issuesByReaderFromRequest.add(i);
            }
        }
        return issuesByReaderFromRequest.size() < maxBookCount;
    }

    public List<Issue> allIssuesByReaderIdBookOnHands(long readerId) {
        List<Issue> allIssues = getAllIssues();
        List<Issue> issuesByReaderId = new ArrayList<>();
        for(Issue i : allIssues) {
            if(i.getIdReader() == readerId && i.getReturnedAt() == null) {
                issuesByReaderId.add(i);
            }
        }
        return issuesByReaderId;
    }

    public Issue returnBook(long issueId) {
        Issue searchIssue = issueRepository.findById(issueId).orElseThrow();
        LocalDateTime returnedAt = LocalDateTime.now();
        searchIssue.setTimeReturn(returnedAt);
        return issueRepository.save(searchIssue);
    }

}
