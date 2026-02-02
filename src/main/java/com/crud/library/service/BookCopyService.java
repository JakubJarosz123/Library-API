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

    public BookCopy addBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public BookCopy changeStatus(Long id, BookStatus status) {
        BookCopy bookCopy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BookCopy with id " + id + " not found!"));
        bookCopy.setStatus(status);

        return bookCopyRepository.save(bookCopy);
    }

    public List<BookCopy> findAllAvailableBookCopies(Long titleId) {
        return bookCopyRepository.findByTitleIdAndStatus(titleId, BookStatus.AVAILABLE);
    }

    public void deleteBookCopy(Long id) {
        bookCopyRepository.deleteById(id);
    }
}
