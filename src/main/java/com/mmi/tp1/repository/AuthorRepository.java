package com.mmi.tp1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mmi.tp1.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
