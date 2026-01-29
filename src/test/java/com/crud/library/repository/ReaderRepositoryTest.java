package com.crud.library.repository;

import com.crud.library.domain.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class ReaderRepositoryTest {

    @Autowired
    private ReaderRepository readerRepository;

    @Test
    void shouldSaveReader() {
        //Given
        Reader reader = new Reader(null, "John", "Smith", LocalDate.now());

        //When
        Reader savedReader = readerRepository.save(reader);

        //Then
        Optional<Reader> foundReader = readerRepository.findById(savedReader.getId());

        assertTrue(foundReader.isPresent());
        assertEquals("John", foundReader.get().getFirstName());
        assertEquals("Smith", foundReader.get().getLastName());
        assertEquals(LocalDate.now(), foundReader.get().getAccountCreated());
    }
}
