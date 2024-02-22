package com.package.generate.controller;


import com.package.generate.repository.StyleRepository;
import com.package.generate.entity.Style;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "style")
public class StyleController
 {

	@Autowired
	private StyleRepository repository;


	@PostMapping()
	public ResponseEntity<Style> save(@RequestBody Style style){
	 	return ResponseEntity.ok(repository.save(style));
	}
	@PutMapping()
	public ResponseEntity<Style> update(@RequestBody Style style){
	 	return ResponseEntity.ok(repository.save(style));
	}
	@DeleteMapping()
	public void delete(@RequestBody Style style){
	 	return ResponseEntity.ok(repository.delete(style));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Style>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
