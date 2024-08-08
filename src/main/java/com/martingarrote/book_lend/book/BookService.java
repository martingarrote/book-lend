package com.martingarrote.book_lend.book;

import com.martingarrote.book_lend.mapper.BookMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper = BookMapper.INSTANCE;

    @Transactional
    private BookDTO save(BookDTO dto) {
        Book entity = mapper.toEntity(dto);

        if (repository.existsByIsbn(entity.getIsbn())) {
            // custom exceptions and handlers will be added later
            throw new RuntimeException();
        }

        return mapper.toDTO(repository.save(entity));
    }

}
