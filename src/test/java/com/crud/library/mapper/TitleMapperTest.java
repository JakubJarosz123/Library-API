package com.crud.library.mapper;

import com.crud.library.domain.Title;
import com.crud.library.domain.dto.TitleDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TitleMapperTest {

    private TitleMapper titleMapper;
    private Title title;
    private TitleDto titleDto;

    @BeforeEach
    void setup() {
        titleMapper = new TitleMapper();
        title = new Title(1L, "LOTR", "Tolkien", 1950);
        titleDto = new TitleDto(1L, "LOTR", "Tolkien", 1950);
    }

    @Test
    void shouldMapToTitleDto() {
        //Given & When
        TitleDto mappedTitleDto = titleMapper.mapToTitleDto(title);
        //Then
        assertEquals(title.getId(), mappedTitleDto.getId());
        assertEquals(title.getTitle(), mappedTitleDto.getTitle());
        assertEquals(title.getAuthor(), mappedTitleDto.getAuthor());
        assertEquals(title.getPublicationYear(), mappedTitleDto.getPublicationYear());
    }

    @Test
    void shouldMapToTitle() {
        //Given & When
        Title mappedTitle = titleMapper.mapToTitle(titleDto);
        //Then
        assertEquals(title.getId(), mappedTitle.getId());
        assertEquals(title.getTitle(), mappedTitle.getTitle());
        assertEquals(title.getAuthor(), mappedTitle.getAuthor());
        assertEquals(title.getPublicationYear(), mappedTitle.getPublicationYear());
    }

    @Test
    void shouldMapToTitlesList() {
        //Given
        List<Title> titles = List.of(
                new Title(1L, "LOTR", "Tolkien", 1950),
                new Title(2L, "Hobbit", "Tolkien", 1930)
        );
        //When
        List<TitleDto> titleDtoList = titleMapper.mapToTitlesList(titles);
        //Then
        assertEquals(titles.size(), titleDtoList.size());
        assertEquals(titles.get(0).getId(), titleDtoList.get(0).getId());
        assertEquals(titles.get(1).getId(), titleDtoList.get(1).getId());
        assertEquals(titles.get(0).getTitle(), titleDtoList.get(0).getTitle());
        assertEquals(titles.get(1).getTitle(), titleDtoList.get(1).getTitle());
        assertEquals(titles.get(0).getAuthor(), titleDtoList.get(0).getAuthor());
        assertEquals(titles.get(1).getAuthor(), titleDtoList.get(1).getAuthor());
        assertEquals(titles.get(0).getPublicationYear(), titleDtoList.get(0).getPublicationYear());
        assertEquals(titles.get(1).getPublicationYear(), titleDtoList.get(1).getPublicationYear());
    }
}
