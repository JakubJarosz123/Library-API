package com.crud.library.service;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import com.crud.library.domain.Title;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BookCopyServiceTest {

    @Autowired
    private BookCopyService bookCopyService;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Test
    void shouldAddBookCopy() {
        //Given
        Title title = titleRepository.save(new Title(null, "LOTR", "Tolkien", 1950))    ;
        BookCopy bookCopy = bookCopyService.addBookCopy(new BookCopy(null, title, BookStatus.AVAILABLE));
        //When

        Optional<BookCopy> saved =  bookCopyRepository.findById(bookCopy.getId());
        //Then
        assertTrue(saved.isPresent());
        assertEquals(BookStatus.AVAILABLE, saved.get().getStatus());
    }

    @Test
    void shouldChangeBookCopyStatus() {
        //Given
        Title title = new Title(null, "LOTR", "Tolkien", 1950);
        BookCopy bookCopy = bookCopyRepository.save(new BookCopy(null, title, BookStatus.AVAILABLE));
        //When
        BookCopy updatedStatus = bookCopyService.changeStatus(bookCopy.getId(), BookStatus.BORROWED);
        //Then
        assertEquals(BookStatus.BORROWED, updatedStatus.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenBookCopyNotFound() {
        //When & Then
        assertThrows(RuntimeException.class, () -> bookCopyService.changeStatus(999L, BookStatus.BORROWED));
    }

    @Test
    void shouldFindAllAvailableBookCopies() {
        //Given
        Title title = titleRepository.save(new Title(null, "LOTR", "Tolkien", 1950));
        bookCopyRepository.save(new BookCopy(null, title, BookStatus.AVAILABLE));
        bookCopyRepository.save(new BookCopy(null, title, BookStatus.BORROWED));
        //When
        List<BookCopy> result = bookCopyService.findAllAvailableBookCopies(title.getId());
        //Then
        assertEquals(1, result.size());
        assertEquals(BookStatus.AVAILABLE, result.get(0).getStatus());
    }

    @Test
    void shouldDeleteBookCopy() {
        //Given
        Title title = titleRepository.save(new Title(null, "LOTR", "Tolkien", 1950));
        BookCopy bookCopy = bookCopyRepository.save(new BookCopy(null, title, BookStatus.AVAILABLE));
        //When
        bookCopyService.deleteBookCopy(bookCopy.getId());
        //Then
        assertFalse(bookCopyRepository.findById(bookCopy.getId()).isPresent());
    }
}
