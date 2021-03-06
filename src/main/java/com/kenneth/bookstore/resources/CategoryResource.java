package com.kenneth.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kenneth.bookstore.domain.Category;
import com.kenneth.bookstore.dtos.CategoryDTO;
import com.kenneth.bookstore.services.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/category")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(final @PathVariable Integer id) {
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		return ResponseEntity.ok()
				.body(service.findAll().stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
		category = service.create(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();
		return ResponseEntity.created(uri).body(category);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO newObj, @PathVariable Integer id) {
		Category objUpdated = service.update(newObj, id);
		return ResponseEntity.ok().body(new CategoryDTO(objUpdated));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(final @PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
