package com.martingarrote.book_lend.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookUpdateDTO(
        Long id,

        @NotBlank(message = "Title cannot be blank")
        String title,

        @NotBlank(message = "Author cannot be blank")
        String author,

        @NotBlank(message = "ISBN cannot be blank")
        @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
        String isbn,

        @NotBlank
        boolean available
) {}
