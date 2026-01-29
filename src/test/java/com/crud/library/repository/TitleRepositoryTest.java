package com.crud.library.repository;

import com.crud.library.domain.Title;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class TitleRepositoryTest {

    @Autowired
    private TitleRepository titleRepository;

    @Test
    void shouldSaveTitle() {
        //Given
        Title title = new Title(null, "Book1", "John Wayne", 2005);

        //When
        Title savedTitle = titleRepository.save(title);

        //Then
        Optional<Title> foundTitle = titleRepository.findById(savedTitle.getId());

        assertTrue(foundTitle.isPresent());
        assertEquals("Book1", foundTitle.get().getTitle());
        assertEquals("John Wayne", foundTitle.get().getAuthor());
        assertEquals(2005, foundTitle.get().getPublicationYear());
    }
}
