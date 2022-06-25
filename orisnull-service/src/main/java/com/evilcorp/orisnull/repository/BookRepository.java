package com.evilcorp.orisnull.repository;

import com.evilcorp.orisnull.entity.Book;
import com.evilcorp.orisnull.filter.BookFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
public interface BookRepository extends JpaRepository<Book, UUID>, BookRepositoryCustom {
    @Query("" +
            " select b from Book b where                                           " +
            " 1=1                                                                  " +
            " and (b.author = :#{#filter.author}   or :#{#filter.author} is null)  " +
            " and (b.rating = :#{#filter.rating}   or :#{#filter.rating} is null)  " +
            " and (b.country = :#{#filter.country} or :#{#filter.country} is null) " +
            " and (b.name = :#{#filter.name}       or :#{#filter.name} is null)    "
    )
    List<Book> findByFilterOrIsNull(@Param("filter") BookFilter filter);

    boolean existsByName(@Param("name") String name);
}
