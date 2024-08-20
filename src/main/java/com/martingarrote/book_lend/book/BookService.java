package com.martingarrote.book_lend.book;

import com.martingarrote.book_lend.mapper.BookMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper = BookMapper.INSTANCE;

    @Transactional
    private BookDTO save(BookDTO dto, boolean isNew) {
        Book entity = mapper.toEntity(dto);

        if (isNew) {
            entity.setAvailable(true);
        }

        if (repository.existsByIsbn(entity.getIsbn())) {
            // custom exceptions and handlers will be added later
            throw new RuntimeException();
        }

        return mapper.toDTO(repository.save(entity));
    }

    public BookDTO create(BookDTO dto) {
        return save(dto, true);
    }

    public List<BookDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public BookDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                // will be changed to custom exception
                .orElseThrow(RuntimeException::new);
    }

}
