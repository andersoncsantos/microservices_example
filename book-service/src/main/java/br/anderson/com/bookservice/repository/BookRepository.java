package br.anderson.com.bookservice.repository;

import br.anderson.com.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
