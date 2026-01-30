package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.domain.dto.ReaderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getId(),
                readerDto.getFirstName(),
                readerDto.getLastName(),
                readerDto.getAccountCreated()
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getAccountCreated()
        );
    }

    public List<ReaderDto> mapToReaderList(List<Reader> readerList) {
        return readerList.stream()
                .map(this::mapToReaderDto)
                .toList();
    }
}
