package com.crud.library.service;

import com.crud.library.domain.Title;
import com.crud.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TitleService {

    private final TitleRepository titleRepository;

    public Title addTitle(String title, String author, int publicationYear) {
        Title titleOne = new Title(null, title, author, publicationYear);

        return titleRepository.save(titleOne);
    }
}
