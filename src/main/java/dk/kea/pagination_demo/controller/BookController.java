package dk.kea.pagination_demo.controller;

import dk.kea.pagination_demo.entity.Book;
import dk.kea.pagination_demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(value = "title", required = false, defaultValue = "") String title,
            @RequestParam(value = "author", required = false, defaultValue = "") String author,
            Pageable pageable) {
        if (author.isEmpty() && title.isEmpty()) {
            return bookService.getAllBooks(pageable);
        } else if (!author.isEmpty() && !title.isEmpty()) {
            return bookService.getAllBooksByTitleAndAuthor(title, author, pageable);
        } else if (!author.isEmpty()) {
            return bookService.getAllBooksByAuthor(author, pageable);
        } else {
            // Handle the case when only the title parameter is present, if needed
            return null;
        }
    }


}

