package com.mmi.tp1.service;

import com.mmi.tp1.model.Book;
import com.mmi.tp1.model.enums.Category;
import com.mmi.tp1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.List;

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
        Iterable<Book> books = bookRepository.findAll();

        List<String> allowedSortFields = List.of("title", "year", "category", "id");
        if (!allowedSortFields.contains(sort)) {
            sort = "title";
        }

        String finalSort = sort;
        return StreamSupport.stream(books.spliterator(), false)
                .filter(book -> title == null || book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(book -> authorId == null || (book.getAuthor() != null && book.getAuthor().getId().equals(authorId)))
                .filter(book -> category == null || category.equals(book.getCategory()))
                .filter(book -> yearFrom == null || book.getYear() >= yearFrom)
                .filter(book -> yearTo == null || book.getYear() <= yearTo)
                .sorted((b1, b2) -> {
                    int directionFactor = direction.equalsIgnoreCase("desc") ? -1 : 1;
                    switch (finalSort) {
                        case "title":
                            return b1.getTitle().compareToIgnoreCase(b2.getTitle()) * directionFactor;
                        case "year":
                            return Integer.compare(b1.getYear(), b2.getYear()) * directionFactor;
                        case "category":
                            return b1.getCategory().compareTo(b2.getCategory()) * directionFactor;
                        case "id":
                        default:
                            return b1.getId().compareTo(b2.getId()) * directionFactor;
                    }
                })
                .collect(Collectors.toList());
    }
}
