package ru.gb.diplom.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.diplom.entity.Reader;
import ru.gb.diplom.repository.ReaderRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReaderUserDetailsService implements UserDetailsService {
    private final ReaderRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader reader = repository.findByName(username).orElseThrow(() ->
                new UsernameNotFoundException("Пользователь " + username + " не найден"));
        return new User(reader.getName(), reader.getPassword(), List.of(
                new SimpleGrantedAuthority(reader.getRole())
        ));
    }
}
