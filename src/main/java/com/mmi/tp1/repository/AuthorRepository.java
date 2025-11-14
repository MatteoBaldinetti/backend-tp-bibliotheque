package com.mmi.tp1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.mmi.tp1.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
