package com.github.ricardocomar.testpyramid.frontend;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.ricardocomar.testpyramid.frontend.action.BookCreateAction;
import com.github.ricardocomar.testpyramid.frontend.model.Book;

@RestController
@RequestMapping(value = "/front/book")
public class BookCreateEndpoint {

	@Autowired
	private BookCreateAction bookAction;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Book> create(@RequestBody Book book)
			throws URISyntaxException {

		bookAction.save(book);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(book.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
