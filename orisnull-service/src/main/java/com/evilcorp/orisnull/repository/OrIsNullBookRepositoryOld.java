package com.evilcorp.orisnull.repository;

import com.evilcorp.orisnull.annotation.OrIsNullQuery;
import com.evilcorp.orisnull.entity.Book;
import com.evilcorp.orisnull.filter.BookFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OrIsNullBookRepositoryOld {

    @Autowired
    EntityManagerFactory emf;

    public List<Book> findByFilterOrIsNull(BookFilter filter) {
        if (filter.isEmpty()) {
            return Collections.emptyList();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("author", filter.getAuthor());
        params.put("country", filter.getCountry());
        params.put("name", filter.getName());
        params.put("rating", filter.getRating());
        String sql = null;
        try {
            sql = OrIsNullBookRepository.class.getMethod("findByFilterOrIsNull", BookFilter.class)
                    .getAnnotation(OrIsNullQuery.class)
                    .value();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        sql = clean(sql, params);
        final var em = emf.createEntityManager();
        final var query = em.createQuery(sql, Book.class);

        params.forEach(query::setParameter);
        return query.getResultList();
    }

    private String clean(String query, Map<String, Object> params) {
        final Set<String> paramNames = params.keySet();
        String transformedQuery = query;
        for (String paramName : paramNames) {
            Pattern pattern = Pattern.compile(":" + paramName + "\\s+is\\s+null", Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(transformedQuery);
            if (params.get(paramName) != null) {
                transformedQuery = matcher.replaceAll("(1!=1)");
            } else {
                transformedQuery = matcher.replaceAll("(1=1)");
            }
        }
        return transformedQuery;
    }
}
