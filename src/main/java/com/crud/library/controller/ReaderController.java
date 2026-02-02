package com.crud.library.controller;

import com.crud.library.domain.Reader;
import com.crud.library.domain.dto.ReaderDto;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;
    private final ReaderMapper mapper;

    @GetMapping
    public ResponseEntity<List<ReaderDto>> getReaders() {
        List<Reader> readers = readerService.getAllReaders();
        return ResponseEntity.ok(mapper.mapToReaderList(readers));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReaderDto> addReader(@RequestBody ReaderDto readerDto) {
        Reader reader = mapper.mapToReader(readerDto);
        Reader saved = readerService.addReader(reader);
        return ResponseEntity.ok(mapper.mapToReaderDto(saved));
    }

    @PutMapping
    public ResponseEntity<ReaderDto> updateReader(@RequestBody ReaderDto readerDto) {
        Reader reader = mapper.mapToReader(readerDto);
        Reader savedReader = readerService.updateReader(reader);
        return ResponseEntity.ok(mapper.mapToReaderDto(savedReader));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
        return ResponseEntity.noContent().build();
    }
}



