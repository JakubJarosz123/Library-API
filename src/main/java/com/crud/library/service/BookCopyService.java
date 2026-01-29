package com.crud.library.service;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import com.crud.library.domain.Title;
import com.crud.library.repository.BookCopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;

    public BookCopy addBookCopy(Title title, BookStatus status) {
        BookCopy bookCopy = new BookCopy(null, title, status);

        return bookCopyRepository.save(bookCopy);
    }

    public BookCopy changeStatus(Long id, BookStatus status) {
        BookCopy bookCopy = bookCopyRepository.findById(id).orElse(null);
        bookCopy.setStatus(status);

        return bookCopyRepository.save(bookCopy);
    }

    public List<BookCopy> findAllAvailableBookCopies(Title title) {
        return bookCopyRepository.findByTitleAndStatus(title, BookStatus.AVAILABLE);
    }
}
