package com.mmi.tp1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mmi.tp1.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
