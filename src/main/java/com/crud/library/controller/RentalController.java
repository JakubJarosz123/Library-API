package com.crud.library.controller;

import com.crud.library.domain.Rental;
import com.crud.library.domain.dto.RentalDto;
import com.crud.library.mapper.RentalMapper;
import com.crud.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rental")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final RentalMapper mapper;

    @PostMapping
    public ResponseEntity<RentalDto> rentBook(@RequestParam Long readerId, @RequestParam Long bookCopyId ) {
        Rental rental = rentalService.rentBook(readerId, bookCopyId);
        return ResponseEntity.ok(mapper.mapToRentalDto(rental));
    }

    @PutMapping
    public ResponseEntity<RentalDto> returnBook(@RequestParam Long readerId, @RequestParam Long bookCopyId) {
        Rental rental = rentalService.returnBook(readerId, bookCopyId);
        return ResponseEntity.ok(mapper.mapToRentalDto(rental));
    }
}