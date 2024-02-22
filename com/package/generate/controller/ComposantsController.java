package com.package.generate.controller;


import com.package.generate.repository.ComposantsRepository;
import com.package.generate.entity.Composants;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "composants")
public class ComposantsController
 {

	@Autowired
	private ComposantsRepository repository;


	@PostMapping()
	public ResponseEntity<Composants> save(@RequestBody Composants composants){
	 	return ResponseEntity.ok(repository.save(composants));
	}
	@PutMapping()
	public ResponseEntity<Composants> update(@RequestBody Composants composants){
	 	return ResponseEntity.ok(repository.save(composants));
	}
	@DeleteMapping()
	public void delete(@RequestBody Composants composants){
	 	return ResponseEntity.ok(repository.delete(composants));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Composants>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
