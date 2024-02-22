package com.package.generate.controller;


import com.package.generate.repository.MaterielRepository;
import com.package.generate.entity.Materiel;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "materiel")
public class MaterielController
 {

	@Autowired
	private MaterielRepository repository;


	@PostMapping()
	public ResponseEntity<Materiel> save(@RequestBody Materiel materiel){
	 	return ResponseEntity.ok(repository.save(materiel));
	}
	@PutMapping()
	public ResponseEntity<Materiel> update(@RequestBody Materiel materiel){
	 	return ResponseEntity.ok(repository.save(materiel));
	}
	@DeleteMapping()
	public void delete(@RequestBody Materiel materiel){
	 	return ResponseEntity.ok(repository.delete(materiel));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Materiel>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
