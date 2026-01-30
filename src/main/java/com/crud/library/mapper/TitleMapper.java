package com.crud.library.mapper;

import com.crud.library.domain.Title;
import com.crud.library.domain.dto.TitleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleMapper {

    public TitleDto mapToTitleDto(final Title title) {
        return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getPublicationYear()
        );
    }

    public Title mapToTitle(final TitleDto titleDto) {
        return new Title(
                titleDto.getId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getPublicationYear()
        );
    }

    public List<TitleDto> mapToTitlesList(List<Title> titlesList) {
        return titlesList.stream()
                .map(this::mapToTitleDto)
                .toList();
    }
}
