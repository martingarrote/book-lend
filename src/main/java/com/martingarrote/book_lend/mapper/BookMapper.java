package com.martingarrote.book_lend.mapper;

import com.martingarrote.book_lend.book.Book;
import com.martingarrote.book_lend.book.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO toDTO(Book book);

    Book toEntity(BookDTO bookDTO);
}
