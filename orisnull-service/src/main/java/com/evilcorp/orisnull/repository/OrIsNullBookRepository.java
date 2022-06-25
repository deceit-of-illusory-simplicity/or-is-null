package com.evilcorp.orisnull.repository;

import com.evilcorp.orisnull.annotation.OrIsNullQuery;
import com.evilcorp.orisnull.annotation.OrIsNullRepository;
import com.evilcorp.orisnull.entity.Book;
import com.evilcorp.orisnull.filter.BookFilter;
import org.springframework.data.repository.query.Param;

import java.util.List;

@OrIsNullRepository
public interface OrIsNullBookRepository {
    //language=HQL
    @OrIsNullQuery(
        " select                                         " +
        "   b                                            " +
        " from                                           " +
        "   Book b                                       " +
        " where                                          " +
        "       1=1                                      " +
        "   and (b.author = :author   or :author is null)  " +
        "   and (b.country = :country or :country is null) " +
        "   and (b.rating = :rating   or :rating is null)  " +
        "   and (b.name = :name       or :name is null)    "
    )
    List<Book> findByFilterOrIsNull(@Param("filter") BookFilter filter);

}
