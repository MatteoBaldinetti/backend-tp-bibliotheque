package com.mmi.tp1.controller;

import com.mmi.tp1.model.Author;
import com.mmi.tp1.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
@CrossOrigin(origins = "*")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return this.authorService.createAuthor(author);
    }

    @GetMapping
    public Iterable<Author> getAllAuthors() {
        return this.authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return this.authorService.getAuthorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthorById(@PathVariable Long id) {
        this.authorService.deleteAuthorById(id);
    }

    @PutMapping("/{id}")
    public Author updateAuthorById(@PathVariable Long id, @RequestBody Author author) {
        return this.authorService.updateAuthor(author, id);
    }
}
