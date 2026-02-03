package com.crud.library.service;

import com.crud.library.domain.*;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.RentalRepository;
import com.crud.library.repository.TitleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class RentalServiceTest {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private RentalRepository rentalRepository;

    private Reader reader;
    private BookCopy bookCopy;

    @BeforeEach
    void setup() {
        reader = readerRepository.save(new Reader(null, "Jeff", "Holmes", LocalDate.now()));
        Title title = titleRepository.save(new Title(null, "Silence Of The Lambs", "John Wayne", 2002));
        bookCopy = bookCopyRepository.save(new BookCopy(null, title, BookStatus.AVAILABLE));
    }

    @Nested
    @DisplayName("Tests for renting book")
    class RentBook {
        @Test
        void shouldRentABook() {
            //When
            Rental rental = rentalService.rentBook(reader.getId(), bookCopy.getId());
            //Then
            assertNotNull(rental.getId());
            assertEquals(reader.getId(), rental.getReader().getId());
            assertEquals(bookCopy.getId(), rental.getBookCopy().getId());
            assertEquals(BookStatus.BORROWED, rental.getBookCopy().getStatus());

            Optional<Rental> savedBook = rentalRepository.findById(rental.getId());
            assertTrue(savedBook.isPresent());
        }

        @Test
        void shouldThrowExceptionWhenBookIsRented() {
            //When
            rentalService.rentBook(reader.getId(), bookCopy.getId());
            //Then
            assertThrows(RuntimeException.class, () -> rentalService.rentBook(reader.getId(), bookCopy.getId()));
        }

        @Test
        void shouldThrowExceptionWhenReaderNotFound() {
            //When
            rentalService.rentBook(reader.getId(), bookCopy.getId());
            //Then
            assertThrows(RuntimeException.class, () -> rentalService.rentBook(999L, bookCopy.getId()));
        }
    }

    @Nested
    @DisplayName("Tests for returning book")
    class ReturnBook {
        @Test
        void shouldReturnABook() {
            //When
            rentalService.rentBook(reader.getId(), bookCopy.getId());
            Rental returned = rentalService.returnBook(reader.getId(), bookCopy.getId());
            //Then
            assertNotNull(returned.getReturnDate());
            assertEquals(BookStatus.AVAILABLE, returned.getBookCopy().getStatus());
        }

        @Test
        void shouldThrowExceptionWhenBookNotRented() {
            assertThrows(RuntimeException.class, () -> rentalService.returnBook(reader.getId(), bookCopy.getId()));
        }

        @Test
        void shouldThrowExceptionWhenDifferentReaderReturnsBook() {
            Reader reader1 = readerRepository.save(new Reader(null, "Jeff", "Holmes", LocalDate.now()));
            Reader reader2 = readerRepository.save(new Reader(null, "John", "Jones", LocalDate.now()));
            Title title = titleRepository.save(new Title(null, "Silence Of The Lambs", "Jeff", 2002));
            BookCopy bookCopy = bookCopyRepository.save(new BookCopy(null, title, BookStatus.AVAILABLE));

            rentalService.rentBook(reader1.getId(), bookCopy.getId());

            RuntimeException exception = assertThrows(RuntimeException.class, () -> rentalService.returnBook(reader2.getId(), bookCopy.getId()));
            assertEquals("Book is rented by different reader.", exception.getMessage());
        }
    }
}