package com.crud.library.repository;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RentalRepository extends CrudRepository<Rental,Long> {

    Optional<Rental> findByBookCopyIdAndReturnDateIsNull(Long bookCopy);
}
