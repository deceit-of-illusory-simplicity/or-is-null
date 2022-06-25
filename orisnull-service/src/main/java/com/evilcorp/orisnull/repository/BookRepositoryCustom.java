package com.evilcorp.orisnull.repository;

import com.evilcorp.orisnull.entity.Book;
import com.evilcorp.orisnull.filter.BookFilter;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> findByFilterJpqlTypical(BookFilter filter);
}
