package com.crud.library.mapper;

import com.crud.library.domain.Rental;
import com.crud.library.domain.dto.RentalDto;
import org.springframework.stereotype.Service;

@Service
public class RentalMapper {

    public RentalDto mapToRentalDto(final Rental rental){
        return new RentalDto(
                rental.getId(),
                rental.getBookCopy().getId(),
                rental.getReader().getId(),
                rental.getRentalDate(),
                rental.getReturnDate()
        );
    }
}
