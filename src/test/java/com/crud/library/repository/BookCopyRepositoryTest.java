package com.crud.library.repository;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import com.crud.library.domain.Title;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class BookCopyRepositoryTest {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Test
    void shouldFindAvailableCopiesByTitleId() {
        //Given
        Title title = titleRepository.save(new Title(null, "Book2", "Margaret Jones", 2010));

        //When
        bookCopyRepository.save(new BookCopy(null, title, BookStatus.AVAILABLE));
        bookCopyRepository.save(new BookCopy(null, title, BookStatus.BORROWED));
        bookCopyRepository.save(new BookCopy(null, title, BookStatus.AVAILABLE));

        //Then
        List<BookCopy> bookCopies = bookCopyRepository.findByTitleIdAndStatus(title, BookStatus.AVAILABLE);

        assertEquals(2,  bookCopies.size());
    }
}
