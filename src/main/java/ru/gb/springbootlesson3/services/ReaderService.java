package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.controllers.restControllers.ReaderRequest;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository bookRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void createReaderDatabase() {
        readerRepository.save(new Reader("Мари"));
        readerRepository.save(new Reader("Саша"));
        readerRepository.save(new Reader("Лиза"));
    }
    public List<Reader> getAllReaders() {
        Iterable<Reader> iterable = readerRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public Reader getReaderById(long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public void deleteReaderbyId(long id) {
        readerRepository.deleteById(id);
    }

    public boolean existsReaderById(long id) {
        return bookRepository.existsById(id);
    }

    public Reader createNewReader(ReaderRequest request) {
        Reader newReader = new Reader(request.getName());
        return readerRepository.save(newReader);
    }

    public String getReaderNameById(long readerId) {
        return readerRepository.findById(readerId).orElseThrow().getName();
    }
}
