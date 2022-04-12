package com.moontech.library.apis;

import com.moontech.library.business.BookBusiness;
import com.moontech.library.constants.RoutesConstant;
import com.moontech.library.models.requests.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * APIS de libros.
 *
 * @author Felipe Monzón
 * @since Dec 20. 2021
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RoutesConstant.BOOKS_BASE_PATH)
public class BookController {
  private final BookBusiness bookBusiness;

  @PostMapping(
      path = RoutesConstant.DATA_CREATE_PATH,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> save(@RequestBody @Valid Book request) {
    this.bookBusiness.saveBook(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping(path = RoutesConstant.DATA_RETRIEVE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Book>> retrieve() {
    return ResponseEntity.ok(this.bookBusiness.retrieve());
  }
}
