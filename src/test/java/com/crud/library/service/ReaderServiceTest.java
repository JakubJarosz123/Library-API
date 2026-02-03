package com.crud.library.service;

import com.crud.library.domain.Reader;
import com.crud.library.repository.ReaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ReaderServiceTest {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private ReaderRepository readerRepository;

    @Test
    void shouldAddReader() {
        //Given
        Reader reader = new Reader(null, "John", "Wayne", LocalDate.now());
        //When
        Reader savedReader = readerService.addReader(reader);
        //Then
        assertNotNull(savedReader.getId());
        assertTrue(readerRepository.findById(savedReader.getId()).isPresent());
    }

    @Test
    void shouldGetAllReaders() {
        //Given
        readerService.addReader(new Reader(null, "John", "Wayne", LocalDate.now()));
        //When
        List<Reader> result = readerService.getAllReaders();
        //Then
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    void shouldDeleteReader() {
        //Given
        Reader reader = readerService.addReader(new Reader(null, "John", "Wayne", LocalDate.now()));
        //When
        readerService.deleteReader(reader.getId());
        //Then
        assertFalse(readerRepository.findById(reader.getId()).isPresent());
    }
}
