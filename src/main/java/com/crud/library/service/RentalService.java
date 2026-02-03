package com.crud.library.service;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Rental;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RentalService {

    private final RentalRepository rentalRepository;
    private final BookCopyRepository bookCopyRepository;
    private final ReaderRepository readerRepository;


    public Rental rentBook(Long readerId, Long bookCopyId) {
        Reader reader = readerRepository.findById(readerId).orElseThrow(() -> new RuntimeException("Reader not found"));
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(() -> new RuntimeException("Book copy not found"));

        Optional<Rental> isAvailable = rentalRepository.findByBookCopyAndReturnDateIsNull(bookCopy);
        if (isAvailable.isPresent()) {
            throw new RuntimeException("Book copy is already rented");
        }

        bookCopy.setStatus(BookStatus.BORROWED);
        Rental rental = new Rental(null, bookCopy, reader, LocalDate.now(), null);

        return rentalRepository.save(rental);
    }

    public Rental returnBook(Long readerId, Long bookCopyId) {
        readerRepository.findById(readerId).orElseThrow(() -> new RuntimeException("Reader not found"));
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(() -> new RuntimeException("Book copy not found"));

        Rental rental = rentalRepository.findByBookCopyAndReturnDateIsNull(bookCopy)
                        .orElseThrow(() -> new RuntimeException("Book copy is not currently rented"));

        if(!rental.getReader().getId().equals(readerId)) {
            throw new RuntimeException("Book is rented by different reader.");
        }

        bookCopy.setStatus(BookStatus.AVAILABLE);
        rental.setReturnDate(LocalDate.now());

        return rental;
    }

}
