package com.mmi.tp1.service;

import com.mmi.tp1.model.Author;
import com.mmi.tp1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        return this.authorRepository.save(author);
    }

    public Author getAuthorById(Long id) {
        return this.authorRepository.findById(id).get();
    }

    public Iterable<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    public void deleteAuthorById(Long id) {
        this.authorRepository.deleteById(id);
    }

    public Author updateAuthor(Author author, Long id) {
        author.setId(id);
        return this.authorRepository.save(author);
    }
}
