package com.mmi.tp1.service;

import com.mmi.tp1.model.Book;
import com.mmi.tp1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
