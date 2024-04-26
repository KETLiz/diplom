package ru.gb.diplom.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.diplom.entity.Issue;
import ru.gb.diplom.annotation.Timer;
import ru.gb.diplom.controllers.restControllers.IssueRequest;
import ru.gb.diplom.repository.BookRepository;
import ru.gb.diplom.repository.IssueRepository;
import ru.gb.diplom.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Timer
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
