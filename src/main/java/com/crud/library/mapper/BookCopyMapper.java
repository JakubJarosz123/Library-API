package com.crud.library.mapper;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.Title;
import com.crud.library.domain.dto.BookCopyDto;
import com.crud.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCopyMapper {

    private final TitleRepository titleRepository;

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        Title title = titleRepository.findById(bookCopyDto.getTitleId()).orElseThrow(() -> new RuntimeException("Title not found"));

        return new BookCopy(
                bookCopyDto.getId(),
                title,
                bookCopyDto.getBookStatus()
        );
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getTitle().getId(),
                bookCopy.getStatus()
        );
    }

    public List<BookCopyDto> mapToBookCopyList(List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(this::mapToBookCopyDto)
                .toList();
    }
}
