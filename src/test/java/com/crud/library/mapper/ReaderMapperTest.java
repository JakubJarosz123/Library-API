package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.domain.dto.ReaderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderMapperTest {

    private ReaderMapper readerMapper;
    private Reader reader;
    private ReaderDto readerDto;

    @BeforeEach
    void setup() {
        readerMapper = new ReaderMapper();
        reader = new Reader(1L, "John", "Wayne", LocalDate.now());
        readerDto = new ReaderDto(1L, "John", "Wayne", LocalDate.now());
    }

    @Test
    void shouldMapToReader() {
        //Given & When
        Reader mappedReader = readerMapper.mapToReader(readerDto);
        //Then
        assertEquals(reader.getId(), mappedReader.getId());
        assertEquals(reader.getFirstName(), mappedReader.getFirstName());
        assertEquals(reader.getLastName(), mappedReader.getLastName());
        assertEquals(reader.getAccountCreated(), mappedReader.getAccountCreated());
    }

    @Test
    void shouldMapToReaderDto() {
        //Given & When
        ReaderDto mappedReaderDto = readerMapper.mapToReaderDto(reader);
        //Then
        assertEquals(readerDto.getId(), mappedReaderDto.getId());
        assertEquals(readerDto.getFirstName(), mappedReaderDto.getFirstName());
        assertEquals(readerDto.getLastName(), mappedReaderDto.getLastName());
        assertEquals(readerDto.getAccountCreated(), mappedReaderDto.getAccountCreated());
    }

    @Test
    void shouldMapToReaderList() {
        //Given
        List<Reader> readers = List.of(
                new Reader(1L, "John", "Wayne", LocalDate.now()),
                new Reader(2L, "Jeremy", "Wade", LocalDate.now()));
        //When
        List<ReaderDto> readerDtoList = readerMapper.mapToReaderList(readers);
        //Then
        assertEquals(readers.size(), readerDtoList.size());
        assertEquals(readers.size(), readerDtoList.size());
        assertEquals(readers.get(0).getId(), readerDtoList.get(0).getId());
        assertEquals(readers.get(1).getId(), readerDtoList.get(1).getId());
        assertEquals(readers.get(0).getFirstName(), readerDtoList.get(0).getFirstName());
        assertEquals(readers.get(1).getFirstName(), readerDtoList.get(1).getFirstName());
        assertEquals(readers.get(0).getLastName(), readerDtoList.get(0).getLastName());
        assertEquals(readers.get(1).getLastName(), readerDtoList.get(1).getLastName());
        assertEquals(readers.get(0).getAccountCreated(), readerDtoList.get(0).getAccountCreated());
        assertEquals(readers.get(1).getAccountCreated(), readerDtoList.get(1).getAccountCreated());
    }
}
