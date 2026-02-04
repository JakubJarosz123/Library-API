package com.crud.library.domain.dto;

import com.crud.library.domain.BookStatus;
import com.crud.library.domain.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyDto {
    private Long id;
    private Long titleId;
    private BookStatus bookStatus;
}
