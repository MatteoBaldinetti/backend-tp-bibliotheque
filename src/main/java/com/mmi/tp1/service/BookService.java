package com.mmi.tp1.service;

import com.mmi.tp1.model.Author;
import com.mmi.tp1.model.Book;
import com.mmi.tp1.model.enums.Category;
import com.mmi.tp1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book book) {
        return this.bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return this.bookRepository.findById(id).get();
    }

    public Iterable<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    public void deleteBookById(Long id) {
        this.bookRepository.deleteById(id);
    }

    public Book updateBook(Book book, Long id) {
        book.setId(id);
        return this.bookRepository.save(book);
    }

    public Iterable<Book> getBooks(
            String title,
            Long authorId,
            Category category,
            Integer yearFrom,
            Integer yearTo,
            String sort,
            String direction
    ) {
        Book probe = new Book();

        if (title != null) probe.setTitle(title);
        if (category != null) probe.setCategory(category);

        if (authorId != null) {
            Author author = new Author();
            author.setId(authorId);
            probe.setAuthor(author);
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        Example<Book> example = Example.of(probe, matcher);

        Sort sortObj = Sort.by(
                direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                sort
        );

        Iterable<Book> books = bookRepository.findAll(example, sortObj);

        // Filter by year range (yearFrom, yearTo)
        if (yearFrom != null || yearTo != null) {
            return StreamSupport.stream(books.spliterator(), false)  // Convert Iterable to Stream
                    .filter(book -> (yearFrom == null || book.getYear() >= yearFrom))  // Filter by yearFrom
                    .filter(book -> (yearTo == null || book.getYear() <= yearTo))  // Filter by yearTo
                    .collect(Collectors.toList());  // Collect into a List
        }

        return books;  // Return filtered and sorted books without additional year filtering
    }
}
