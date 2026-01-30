package com.crud.library.repository;

import com.crud.library.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class RentalRepositoryTest {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Test
    void shouldFindActiveRentalByBookCopyId() {
        //Given
        Reader reader = readerRepository.save(new Reader(null, "John", "Smith", LocalDate.now()));

        Title title = titleRepository.save(new Title(null, "Book Copy", "John Wayne", 2005));

        BookCopy bookCopy = bookCopyRepository.save(new BookCopy(null, title, BookStatus.BORROWED));

        rentalRepository.save(new Rental(null, bookCopy, reader, LocalDate.now(), null));

        //When
        Optional<Rental> activeRental = rentalRepository.findByBookCopyAndReturnDateIsNull(bookCopy);

        //Then
        assertTrue(activeRental.isPresent());
        assertEquals(reader.getId(), activeRental.get().getReader().getId());
    }
}
