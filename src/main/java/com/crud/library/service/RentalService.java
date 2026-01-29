package com.crud.library.service;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Rental;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.RentalRepository;
import com.crud.library.repository.TitleRepository;
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

        Optional<Rental> isAvailable = rentalRepository.findByBookCopyIdAndReturnDateIsNull(bookCopy);
        if (isAvailable.isPresent()) {
            throw new RuntimeException("Book copy is already rented");
        }

        bookCopy.setStatus(BookStatus.BORROWED);
        Rental rental = new Rental(null, bookCopy, reader, LocalDate.now(), null);

        return rentalRepository.save(rental);
    }

    public Rental returnBook(Long readerId, Long bookCopyId) {
        Reader reader = readerRepository.findById(readerId).orElseThrow(() -> new RuntimeException("Reader not found"));
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(() -> new RuntimeException("Book copy not found"));

        Rental rental = rentalRepository.findByBookCopyIdAndReturnDateIsNull(bookCopy)
                        .orElseThrow(() -> new RuntimeException("Book copy is not currently rented"));

        bookCopy.setStatus(BookStatus.AVAILABLE);
        rental.setReturnDate(LocalDate.now());

        return rentalRepository.save(rental);
    }

}
