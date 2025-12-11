package com.mmi.tp1.service;

import com.mmi.tp1.model.Book;
import com.mmi.tp1.model.enums.Category;
import com.mmi.tp1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            String direction,
            Integer page,
            Integer size
    ) {
        Iterable<Book> books = bookRepository.findAll();

        List<String> allowedSortFields = List.of("title", "year", "category", "id");
        if (!allowedSortFields.contains(sort)) {
            sort = "title";
        }
        String finalSort = sort;

        List<Book> filtered = StreamSupport.stream(books.spliterator(), false)
                .filter(book -> title == null || book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(book -> authorId == null || (book.getAuthor() != null && book.getAuthor().getId().equals(authorId)))
                .filter(book -> category == null || category.equals(book.getCategory()))
                .filter(book -> yearFrom == null || book.getYear() >= yearFrom)
                .filter(book -> yearTo == null || book.getYear() <= yearTo)
                .sorted((b1, b2) -> {
                    int factor = "desc".equalsIgnoreCase(direction) ? -1 : 1;
                    return switch (finalSort) {
                        case "title" -> b1.getTitle().compareToIgnoreCase(b2.getTitle()) * factor;
                        case "year" -> Integer.compare(b1.getYear(), b2.getYear()) * factor;
                        case "category" -> b1.getCategory().compareTo(b2.getCategory()) * factor;
                        case "id" -> b1.getId().compareTo(b2.getId()) * factor;
                        default -> 0;
                    };
                })
                .toList();

        if (page == null || page < 0) page = 0;
        if (size == null || size <= 0) size = 10;

        int fromIndex = page * size;
        if (fromIndex >= filtered.size()) return List.of();

        int toIndex = Math.min(fromIndex + size, filtered.size());

        return filtered.subList(fromIndex, toIndex);
    }
}
