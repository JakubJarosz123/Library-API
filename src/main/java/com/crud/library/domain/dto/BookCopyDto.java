package com.crud.library.domain.dto;

import com.crud.library.domain.BookStatus;
import com.crud.library.domain.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookCopyDto {
    private Long id;
    private Title title;
    private BookStatus bookStatus;
}
