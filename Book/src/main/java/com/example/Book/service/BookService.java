package com.example.Book.service;

import com.example.Book.model.Book;
import com.example.Book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //create constructor for required fields
public class BookService {
    private final BookRepository bookRepository;

//    public BookService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Long id, Book book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setIsbn(book.getIsbn());
                    existingBook.setPublishedDate(book.getPublishedDate());
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
