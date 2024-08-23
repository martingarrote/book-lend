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
    private BookDTO save(Long id, Book book, boolean isPut) {
        if (id == null) {
            book.setAvailable(true);
        }

        book.setId(id);

        if (!isPut && !(book.getIsbn() == null)) {
            if (repository.existsByIsbn(book.getIsbn())) {
                throw new RuntimeException();
            }
        }

        return mapper.toDTO(repository.save(book));
    }

    public BookDTO create(BookDTO dto) {
        return save(null, mapper.toEntity(dto), false);
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

    public BookDTO update(Long id, BookUpdateDTO updateDTO) {
        // put won't create new entities
        if (!repository.existsById(id)) {
            // will be changed to custom exception
            throw new RuntimeException();
        }

        return save(id, mapper.toEntity(updateDTO), true);
    }

}
