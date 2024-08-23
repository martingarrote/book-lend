package com.martingarrote.book_lend.book;

import com.martingarrote.book_lend.book.dto.BookDTO;
import com.martingarrote.book_lend.book.dto.BookUpdateDTO;
import com.martingarrote.book_lend.mapper.BookMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper = BookMapper.INSTANCE;

    @Transactional
    private BookDTO save(BookDTO dto, boolean isNew, boolean isPatch) {
        Book entity = mapper.toEntity(dto);

        if (isNew) {
            entity.setAvailable(true);
        }

        if (repository.existsByIsbn(entity.getIsbn()) && (isNew || isPatch)) {
            // custom exceptions and handlers will be added later
            throw new RuntimeException();
        }

        return mapper.toDTO(repository.save(entity));
    }

    public BookDTO create(BookDTO dto) {
        return save(dto, true, false);
    }

    public BookDTO update(BookUpdateDTO updateDTO) {
        Long id = updateDTO.id();

        // put won't create new entities
        if (!repository.existsById(id)) {
            // will be changed to custom exception
            throw new RuntimeException();
        }

        return save(mapper.toDTO(updateDTO), false, false);
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
