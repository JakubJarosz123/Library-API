package com.crud.library.service;

import com.crud.library.domain.Reader;
import com.crud.library.repository.ReaderRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class ReaderService {

    private final ReaderRepository readerRepository;

    public Reader addReader(String firstName, String lastName){
        Reader reader = new Reader(null, firstName, lastName, LocalDate.now());

        return readerRepository.save(reader);
    }
}