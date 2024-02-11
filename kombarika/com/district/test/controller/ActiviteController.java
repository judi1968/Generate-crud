package com.district.test.controller;


import com.district.test.repository.ActiviteRepository;
import com.district.test.entity.Activite;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "activite")
public class ActiviteController
 {

	@Autowired
	private ActiviteRepository repository;


	@PostMapping()
	public ResponseEntity<Activite> save(@RequestBody Activite activite){
	 	return ResponseEntity.ok(repository.save(activite));
	}
	@PutMapping()
	public ResponseEntity<Activite> update(@RequestBody Activite activite){
	 	return ResponseEntity.ok(repository.save(activite));
	}
	@DeleteMapping()
	public void delete(@RequestBody Activite activite){
	 	return ResponseEntity.ok(repository.delete(activite));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Activite>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
