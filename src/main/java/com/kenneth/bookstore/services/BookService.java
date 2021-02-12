package com.kenneth.bookstore.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenneth.bookstore.domain.Book;
import com.kenneth.bookstore.exceptions.ObjetoNotFoundException;
import com.kenneth.bookstore.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book findById(final Integer id) {
		Optional<Book> obj = bookRepository.findById(id);
		return obj.orElseThrow(() -> new ObjetoNotFoundException(
				"the object was not found, id:" + id + ", type: " + ObjetoNotFoundException.class.getName()));
	}

}