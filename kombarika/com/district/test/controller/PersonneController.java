package com.district.test.controller;


import com.district.test.repository.PersonneRepository;
import com.district.test.entity.Personne;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "personne")
public class PersonneController
 {

	@Autowired
	private PersonneRepository repository;


	@PostMapping()
	public ResponseEntity<Personne> save(@RequestBody Personne personne){
	 	return ResponseEntity.ok(repository.save(personne));
	}
	@PutMapping()
	public ResponseEntity<Personne> update(@RequestBody Personne personne){
	 	return ResponseEntity.ok(repository.save(personne));
	}
	@DeleteMapping()
	public void delete(@RequestBody Personne personne){
	 	return ResponseEntity.ok(repository.delete(personne));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Personne>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
