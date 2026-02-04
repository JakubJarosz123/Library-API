package com.crud.library.service;

import com.crud.library.domain.Reader;
import com.crud.library.repository.ReaderRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReaderService {

    private final ReaderRepository readerRepository;

    public Reader addReader(Reader reader){
        return readerRepository.save(reader);
    }

    public List<Reader> getAllReaders(){
        return (List<Reader>) readerRepository.findAll();
    }

    public Reader updateReader(Reader reader) {
        Reader readerFromDb = readerRepository.findById(reader.getId()).orElseThrow(() -> new RuntimeException("Reader Not Found"));
        readerFromDb.setFirstName(reader.getFirstName());
        readerFromDb.setLastName(reader.getLastName());
        readerFromDb.setAccountCreated(reader.getAccountCreated());

        return readerRepository.save(readerFromDb);
    }

    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }
}