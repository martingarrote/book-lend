package com.martingarrote.book_lend.mapper;

import com.martingarrote.book_lend.book.Book;
import com.martingarrote.book_lend.book.dto.BookDTO;
import com.martingarrote.book_lend.book.dto.BookUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO toDTO(Book book);
    BookDTO toDTO(BookUpdateDTO updateDTO);

    Book toEntity(BookDTO bookDTO);
}
