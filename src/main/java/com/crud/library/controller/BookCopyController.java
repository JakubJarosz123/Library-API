package com.crud.library.controller;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import com.crud.library.domain.dto.BookCopyDto;
import com.crud.library.mapper.BookCopyMapper;
import com.crud.library.service.BookCopyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bookCopy")
@RequiredArgsConstructor
public class BookCopyController {

    private final BookCopyService bookCopyService;
    private final BookCopyMapper mapper;

    @GetMapping("/{titleId}")
    public ResponseEntity<List<BookCopyDto>> getBookCopies(@PathVariable Long titleId) {
        List<BookCopy> bookCopies = bookCopyService.findAllAvailableBookCopies(titleId);
        return ResponseEntity.ok(mapper.mapToBookCopyList(bookCopies));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookCopyDto> addBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        BookCopy bookCopy = mapper.mapToBookCopy(bookCopyDto);
        BookCopy saved = bookCopyService.addBookCopy(bookCopy);
        return ResponseEntity.ok(mapper.mapToBookCopyDto(saved));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookCopyDto> changeStatusOfBookCopy(@PathVariable Long id, @RequestParam BookStatus bookStatus) {
        BookCopy savedBookCopy = bookCopyService.changeStatus(id, bookStatus);
        return ResponseEntity.ok(mapper.mapToBookCopyDto(savedBookCopy));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteBookCopy(@PathVariable Long id) {
        bookCopyService.deleteBookCopy(id);
        return ResponseEntity.noContent().build();
    }
}
