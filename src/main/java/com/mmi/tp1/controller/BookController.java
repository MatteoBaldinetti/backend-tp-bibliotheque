package com.mmi.tp1.controller;

import com.mmi.tp1.model.Book;
import com.mmi.tp1.model.enums.Category;
import com.mmi.tp1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return this.bookService.createBook(book);
    }

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return this.bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return this.bookService.getBookById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        this.bookService.deleteBookById(id);
    }

    @PutMapping("/{id}")
    public Book updateBookById(@PathVariable Long id, @RequestBody Book book) {
        return this.bookService.updateBook(book, id);
    }

    @GetMapping("/filter")
    public List<Book> filterBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Integer yearFrom,
            @RequestParam(required = false) Integer yearTo,
            @RequestParam(defaultValue = "title") String sort,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Iterable<Book> books = bookService.getBooks(title, authorId, category, yearFrom, yearTo, sort, direction);

        return (List<Book>) books;
    }
}
