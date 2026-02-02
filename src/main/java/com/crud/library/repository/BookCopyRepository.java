package com.crud.library.repository;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy,Long> {

    List<BookCopy> findByTitleIdAndStatus(Long titleId, BookStatus status);
}
