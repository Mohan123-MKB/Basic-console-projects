package com.example.Book.controller;


import com.example.Book.model.Book;
import com.example.Book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books") //mapping any http method to handle web request
@RequiredArgsConstructor //automatically create constructor for required fields
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getAll(){
        return bookService.getAll();
    }
    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id){
        return bookService.getById(id).orElse(null);
    }
    @PostMapping
    public Book create(@RequestBody Book book){
        return bookService.create(book);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        try {
            return ResponseEntity.ok(bookService.update(id, book));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        bookService.delete(id); //next layer and method to return value
    }
}







//    @GetMapping("/{id}")
//    public ResponseEntity<Book> getById(@PathVariable Long id) {
//        return bookService.getById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

//    @PostMapping
//    public ResponseEntity<Book> create(@RequestBody Book book) {
//        return new ResponseEntity<>(bookService.create(book), HttpStatus.CREATED);
//    }


//@PutMapping("/{id}")
//public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
//    try {
//        return ResponseEntity.ok(bookService.update(id, book));
//    } catch (RuntimeException e) {
//        return ResponseEntity.notFound().build();
//    }
//}


//@DeleteMapping("/{id}")
//public ResponseEntity<Void> delete(@PathVariable Long id) {
//    bookService.delete(id);
//    return ResponseEntity.noContent().build();
//}