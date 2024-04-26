package ru.gb.diplom.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.diplom.entity.Reader;
import ru.gb.diplom.controllers.restControllers.ReaderRequest;
import ru.gb.diplom.repository.IssueRepository;
import ru.gb.diplom.repository.ReaderRepository;

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
        Reader reader1 = new Reader("Mari");
        reader1.setPassword("Mari");
        reader1.setRole("user");
        Reader reader2 = new Reader("Sasha");
        reader2.setPassword("456");
        reader2.setRole("user");
        Reader reader3 = new Reader("Liza");
        reader3.setPassword("789");
        reader3.setRole("admin");

        readerRepository.save(reader1);
        readerRepository.save(reader2);
        readerRepository.save(reader3);
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

    public Reader findByName(String name) {
        return readerRepository.findByName(name).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Нет пользователя с " +
                        "именем " + name));
    }
}
