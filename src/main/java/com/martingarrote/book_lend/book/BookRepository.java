package com.martingarrote.book_lend.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE " +
            "(:title IS NULL OR b.title LIKE %:title%) AND " +
            "(:author IS NULL OR b.author LIKE %:author%) AND " +
            "(:isbn IS NULL OR b.isbn = :isbn) AND " +
            "(:available IS NULL OR b.available = :available)")
    Page<Book> search(@Param("title") String title,
                      @Param("author") String author,
                      @Param("isbn") String isbn,
                      @Param("available") Boolean available,
                      Pageable pageable);

    boolean existsByIsbn(String isbn);
    boolean existsByIsbnAndIdNot(String isbn, Long id);
}