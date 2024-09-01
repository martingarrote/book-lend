package com.martingarrote.book_lend.book;

import com.martingarrote.book_lend.book.dto.BookDTO;
import com.martingarrote.book_lend.book.dto.BookPatchDTO;
import com.martingarrote.book_lend.book.dto.BookUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Boolean available) {

        return ResponseEntity.status(HttpStatus.OK).body(service.search(title, author, isbn, available));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> fullUpdate(@PathVariable Long id, @RequestBody BookUpdateDTO updateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.fullUpdate(id, updateDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDTO> partialUpdate(@PathVariable Long id, @RequestBody BookPatchDTO patchDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.partialUpdate(id, patchDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
