package com.crud.library.mapper;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import com.crud.library.domain.Title;
import com.crud.library.domain.dto.BookCopyDto;
import com.crud.library.repository.TitleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookCopyMapperTest {

    @Mock
    private TitleRepository titleRepository;

    @InjectMocks
    private BookCopyMapper bookCopyMapper;

    private BookCopy bookCopy;
    private BookCopyDto bookCopyDto;
    private Title title;

    @BeforeEach
    void setup() {
        title = new Title(1L, "LOTR", "Tolkien", 1950);
        bookCopy = new BookCopy(1L, title, BookStatus.AVAILABLE);
        bookCopyDto = new BookCopyDto(1L, title.getId(), BookStatus.AVAILABLE);

        when(titleRepository.findById(1L))
                .thenReturn(Optional.of(title));
    }

    @Test
    void shouldMapToBookCopyDto() {
        //Given & When
        BookCopyDto mappedBookCopyDto = bookCopyMapper.mapToBookCopyDto(bookCopy);
        //Then
        assertEquals(bookCopy.getId(), mappedBookCopyDto.getId());
        assertEquals(bookCopy.getTitle().getId(), mappedBookCopyDto.getTitleId());
        assertEquals(bookCopy.getStatus(), mappedBookCopyDto.getBookStatus());
    }

    @Test
    void shouldMapToBookCopy() {
        //Given & When
        BookCopy mappedBookCopy = bookCopyMapper.mapToBookCopy(bookCopyDto);
        //Then
        assertEquals(bookCopy.getId(), mappedBookCopy.getId());
        assertEquals(bookCopy.getTitle(), mappedBookCopy.getTitle());
        assertEquals(bookCopy.getStatus(), mappedBookCopy.getStatus());
    }

    @Test
    void shouldMapToBookCopyList() {
        //Given
        List<BookCopy> bookCopies = List.of(
                new BookCopy(1L, title, BookStatus.AVAILABLE),
                new BookCopy(2L, title, BookStatus.AVAILABLE)
        );
        //When
        List<BookCopyDto> bookCopyDtoList = bookCopyMapper.mapToBookCopyList(bookCopies);
        //Then
        assertEquals(bookCopies.size(), bookCopyDtoList.size());
        assertEquals(bookCopies.get(0).getId(), bookCopyDtoList.get(0).getId());
        assertEquals(bookCopies.get(1).getId(), bookCopyDtoList.get(1).getId());
        assertEquals(bookCopies.get(0).getTitle().getId(), bookCopyDtoList.get(0).getTitleId());
        assertEquals(bookCopies.get(1).getTitle().getId(), bookCopyDtoList.get(1).getTitleId());
        assertEquals(bookCopies.get(0).getStatus(), bookCopyDtoList.get(0).getBookStatus());
        assertEquals(bookCopies.get(1).getStatus(), bookCopyDtoList.get(1).getBookStatus());
    }
}
