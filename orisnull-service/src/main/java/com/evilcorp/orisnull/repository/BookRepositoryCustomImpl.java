package com.evilcorp.orisnull.repository;

import com.evilcorp.orisnull.entity.Book;
import com.evilcorp.orisnull.filter.BookFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;

@Service
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @Autowired
    EntityManagerFactory emf;


    @Override
    public List<Book> findByFilterJpqlTypical(BookFilter filter) {
        if (filter.isEmpty()) {
            return Collections.emptyList();
        }
        String sql = " select b from Book b where 1=1  "
                + (filter.getAuthor() == null ? "" :
                " and b.author = :author  "
        )
                + (filter.getCountry() == null ? "" :
                " and b.country = :country"
        )
                + (filter.getRating() == null ? "" :
                " and b.rating = :rating  "
        )
                + (filter.getName() == null ? "" :
                " and b.name = :name      "
        );

        final var em = emf.createEntityManager();
        final var query = em.createQuery(sql, Book.class);

        if (filter.getAuthor() != null) {
            query.setParameter("author", filter.getAuthor());
        }
        if (filter.getCountry() != null) {
            query.setParameter("country", filter.getCountry());
        }
        if (filter.getName() != null) {
            query.setParameter("name", filter.getName());
        }
        if (filter.getRating() != null) {
            query.setParameter("rating", filter.getRating());
        }

        return query.getResultList();
    }

}
