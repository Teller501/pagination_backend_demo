package dk.kea.pagination_demo.service;

import dk.kea.pagination_demo.entity.Book;
import dk.kea.pagination_demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Page<Book> getAllBooksByAuthor(String author, Pageable pageable) {
        return bookRepository.findAllByAuthorLikeIgnoreCase(author, pageable);
    }

    public Page<Book> getAllBooksByTitleAndAuthor(String title, String author, Pageable pageable) {
        return bookRepository.findByTitleAndAuthorContainingIgnoreCase(title, author, pageable);
    }


}
