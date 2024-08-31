package com.martingarrote.book_lend.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookPatchDTO(

        Long id,

        String title,

        String author,

        @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
        String isbn,

        Boolean available
) {}
