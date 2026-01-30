package com.crud.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ReaderDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate accountCreated;
}
