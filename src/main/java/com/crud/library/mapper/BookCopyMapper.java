package com.crud.library.mapper;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.dto.BookCopyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCopyMapper {

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getTitle(),
                bookCopyDto.getBookStatus()
        );
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getTitle(),
                bookCopy.getStatus()
        );
    }

    public List<BookCopyDto> mapToBookCopyList(List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(this::mapToBookCopyDto)
                .toList();
    }
}
