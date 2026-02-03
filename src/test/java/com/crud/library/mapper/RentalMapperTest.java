package com.crud.library.mapper;

import com.crud.library.domain.*;
import com.crud.library.domain.dto.RentalDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalMapperTest {

    private RentalMapper mapper;
    private Rental rental;
    private RentalDto rentalDto;
    private BookCopy bookCopy;
    private Title title;
    private Reader reader;

    @BeforeEach
    void setup() {
        mapper = new RentalMapper();
        reader = new Reader(1L, "John", "Wayne", LocalDate.now());
        title = new Title(1L, "LOTR", "Tolkien", 1950);
        bookCopy = new BookCopy(1L, title, BookStatus.AVAILABLE);
        rental = new Rental(1L, bookCopy, reader, LocalDate.of(2025, 4, 22), LocalDate.of(2025, 4, 22));
        rentalDto = new RentalDto(1L, bookCopy.getId(), reader.getId(), rental.getRentalDate(), rental.getReturnDate());
    }

    @Test
    void shouldMapToRentalDto() {
        //Given & When
        RentalDto rentalDto = mapper.mapToRentalDto(rental);
        //Then
        assertEquals(rental.getId(), rentalDto.getId());
        assertEquals(rental.getBookCopy().getId(), rentalDto.getBookCopyId());
        assertEquals(rental.getReader().getId(), rentalDto.getReaderId());
        assertEquals(rental.getRentalDate(), rentalDto.getRentalDate());
        assertEquals(rental.getReturnDate(), rentalDto.getReturnDate());
    }
}
