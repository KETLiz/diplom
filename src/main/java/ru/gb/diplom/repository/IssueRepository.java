package ru.gb.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.diplom.entity.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

}
