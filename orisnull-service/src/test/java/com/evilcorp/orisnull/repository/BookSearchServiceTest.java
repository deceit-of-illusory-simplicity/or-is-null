package com.evilcorp.orisnull.repository;

import com.evilcorp.orisnull.entity.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.evilcorp.orisnull.filter.BookFilterBuilder.book;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookSearchServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrIsNullBookRepository orIsNullBookRepository;

    @Test
    void autowires() {
       assertThat(bookRepository).isNotNull();
    }

    @Nested
    class GenTest {
        @Test
        void nonExistingFilter() {
            final var pelevin = orIsNullBookRepository.findByFilterOrIsNull(book()
                    .build());
            assertEquals(0, pelevin.size());
        }

        @Test
        void existingFilter() {
            final var pelevin = orIsNullBookRepository.findByFilterOrIsNull(book()
                    .author("Pelevin")
                    .rating(4)
                    .build());
            assertEquals(1, pelevin.size());
        }
    }

    @BeforeAll
    public void setUp() {
        final var books = List.of(
                new Book(
                        "Lord of the rings",
                        "Tolkien",
                        5,
                        "Great Britain"
                ),
                new Book(
                        "Harry Potter",
                        "Rowling",
                        5,
                        "Great Britain"
                ),
                new Book(
                        "Sol Invictus",
                        "Pelevin",
                        4,
                        "Russia"
                ),
                new Book(
                        "Вор в Законе",
                        "Лютый",
                        3,
                        "Russia"
                ),
                new Book(
                        "Generation P",
                        "Pelevin",
                        5,
                        "Russia"
                )
        );
        bookRepository.saveAll(books);
    }
}
