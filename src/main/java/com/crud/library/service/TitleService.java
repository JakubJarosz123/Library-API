package com.crud.library.service;

import com.crud.library.domain.Title;
import com.crud.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TitleService {

    private final TitleRepository titleRepository;

    public Title addTitle(Title title) {
        return titleRepository.save(title);
    }

    public List<Title> getAllTitles() {
        return (List<Title>) titleRepository.findAll();
    }

    public Title updateTitle(Title title) {
        Title titleFromDb = titleRepository.findById(title.getId()).orElseThrow(() -> new RuntimeException("Title id not found"));
        titleFromDb.setTitle(title.getTitle());
        titleFromDb.setAuthor(title.getAuthor());
        titleFromDb.setPublicationYear(title.getPublicationYear());

        return titleFromDb;
    }

    public void deleteTitle(Long id) {
        titleRepository.deleteById(id);
    }
}
