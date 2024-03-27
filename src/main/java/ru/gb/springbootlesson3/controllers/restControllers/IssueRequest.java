package ru.gb.springbootlesson3.controllers.restControllers;

import lombok.Data;

@Data
public class IssueRequest {
    private long readerId;
    private long bookId;
}
