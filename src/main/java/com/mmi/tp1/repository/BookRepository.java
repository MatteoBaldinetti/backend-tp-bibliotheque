package com.mmi.tp1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.mmi.tp1.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
