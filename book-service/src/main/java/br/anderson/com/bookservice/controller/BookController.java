package br.anderson.com.bookservice.controller;

import br.anderson.com.bookservice.model.Book;
import br.anderson.com.bookservice.proxy.CambioProxy;
import br.anderson.com.bookservice.repository.BookRepository;
import br.anderson.com.bookservice.response.Cambio;
import java.util.Objects;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "Book Endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;

    /*@GetMapping(value = "{id}/{currency}")
    public Book findByBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

        try {
            Book book = repository.getReferenceById(id);

            HashMap<String, String> params = new HashMap<>();
            params.put("amount", book.getPrice().toString());
            params.put("from", "USD");
            params.put("to", currency);

            ResponseEntity<Cambio> response = new RestTemplate()
                    .getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class, params);

            Cambio cambio = response.getBody();

            String port = environment.getProperty("local.server.port");
            book.setEnvironment(port);
            book.setPrice(Objects.nonNull(cambio) ? cambio.getConvertedValue() : null);

            return book;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message");
        }
    }*/

    @Operation(summary = "Find book by id")
    @GetMapping(value = "{id}/{currency}")
    public Book findByBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

        try {
            Book book = repository.getReferenceById(id);

            Cambio cambio = proxy.getCambio(book.getPrice(), "USD", currency);

            String port = environment.getProperty("local.server.port");
            book.setEnvironment("Book port: " + port + " Cambio port: " + (Objects.nonNull(cambio) ? cambio.getEnvironment() : null));
            book.setPrice(Objects.nonNull(cambio) ? cambio.getConvertedValue() : null);

            return book;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message");
        }
    }

}
