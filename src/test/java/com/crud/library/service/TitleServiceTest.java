package com.crud.library.service;

import com.crud.library.domain.Title;
import com.crud.library.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class TitleServiceTest {

    @Autowired
    private TitleService titleService;

    @Autowired
    private TitleRepository titleRepository;

    @Test
    void shouldAddTitle() {
        //Given & When
        Title title = titleService.addTitle(new Title(null, "LOTR", "Tolkien", 1950));
        //Then
        assertNotNull(titleRepository.findById(title.getId()));
        assertTrue(titleRepository.findById(title.getId()).isPresent());
    }

    @Test
    void shouldShowAllTitles() {
        //Given
        titleService.addTitle(new Title(null, "LOTR", "Tolkien", 1950));
        //When
        List<Title> result = titleService.getAllTitles();
        //Then
        assertEquals(1, result.size());
        assertEquals("LOTR", result.get(0).getTitle());
    }

    @Test
    void shouldUpdateTitle() {
        //Given
        Title saved = titleService.addTitle(new Title(null, "LOTR", "Tolkien", 1950));
        //When
        saved.setTitle("Hobbit");
        saved.setPublicationYear(1930);
        titleService.updateTitle(saved);
        //Then
        Title updated = titleRepository.findById(saved.getId()).orElseThrow();
        assertEquals("Hobbit", updated.getTitle());
        assertEquals(1930, updated.getPublicationYear());
    }

    @Test
    void shouldDeleteTitle() {
        //Given
        Title title = titleService.addTitle(new Title(null, "LOTR", "Tolkien", 1950));
        //When
        titleService.deleteTitle(title.getId());
        //Then
        assertFalse(titleRepository.findById(title.getId()).isPresent());
    }
}
