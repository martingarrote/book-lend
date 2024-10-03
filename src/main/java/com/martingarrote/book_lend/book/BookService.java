package com.martingarrote.book_lend.book;

import com.martingarrote.book_lend.book.dto.BookDTO;
import com.martingarrote.book_lend.book.dto.BookPatchDTO;
import com.martingarrote.book_lend.book.dto.BookUpdateDTO;
import com.martingarrote.book_lend.mapper.BookMapper;
import com.martingarrote.book_lend.mapper.common.PageDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper = BookMapper.INSTANCE;

    public BookDTO create(BookDTO dto) {

        if (repository.existsByIsbn(dto.isbn())) {
            throw new RuntimeException("ISBN already exists");
        }

        Book book = mapper.toEntity(dto);

        book.setAvailable(true);

        return mapper.toDTO(repository.save(book));
    }

    public List<BookDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public BookDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(RuntimeException::new);
    }

    public PageDTO<BookDTO> search(String title, String author, String isbn, Boolean available, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> result = repository.search(title, author, isbn, available, pageable);

        return new PageDTO<>(
                result.getContent().stream().map(mapper::toDTO).toList(),
                result.getNumber(),
                result.getSize(),
                result.getNumberOfElements()
        );
    }

    public BookDTO fullUpdate(Long id, BookUpdateDTO updateDTO) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Book not found with id " + id);
        }

        if (repository.existsByIsbnAndIdNot(updateDTO.isbn(), id)) {
            throw new RuntimeException("ISBN already exists");
        }

        Book book = mapper.toEntity(updateDTO);
        book.setId(id);

        return mapper.toDTO(repository.save(book));
    }

    public BookDTO partialUpdate(Long id, BookPatchDTO patchDTO) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        if (patchDTO.title() != null) {
            book.setTitle(patchDTO.title());
        }
        if (patchDTO.author() != null) {
            book.setAuthor(patchDTO.author());
        }
        if (patchDTO.isbn() != null) {

            if (repository.existsByIsbn(patchDTO.isbn())) {
                throw new RuntimeException("ISBN already exists");
            }

            book.setIsbn(patchDTO.isbn());
        }
        if (patchDTO.available() != null) {
            book.setAvailable(patchDTO.available());
        }

        return mapper.toDTO(repository.save(book));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }

        repository.deleteById(id);
    }

}
