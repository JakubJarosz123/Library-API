package com.crud.library.controller;

import com.crud.library.domain.Title;
import com.crud.library.domain.dto.TitleDto;
import com.crud.library.mapper.TitleMapper;
import com.crud.library.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/titles")
@RequiredArgsConstructor
public class TitleController {

    private final TitleService titleService;
    private final TitleMapper mapper;

    @GetMapping
    public ResponseEntity<List<TitleDto>> getTitles() {
        List<Title> titles = titleService.getAllTitles();
        return ResponseEntity.ok(mapper.mapToTitlesList(titles));
    }

    @PutMapping
    public ResponseEntity<TitleDto> updateTitle(@RequestBody TitleDto titleDto) {
        Title title = mapper.mapToTitle(titleDto);
        Title savedTitle = titleService.updateTitle(title);
        return ResponseEntity.ok(mapper.mapToTitleDto(savedTitle));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TitleDto> addTitle(@RequestBody TitleDto titleDto) {
        Title title = mapper.mapToTitle(titleDto);
        Title saved = titleService.addTitle(title);
        return ResponseEntity.ok(mapper.mapToTitleDto(saved));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteTitle(@PathVariable Long id) {
        titleService.deleteTitle(id);
        return ResponseEntity.noContent().build();
    }
}