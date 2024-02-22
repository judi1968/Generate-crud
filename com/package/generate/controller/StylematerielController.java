package com.package.generate.controller;


import com.package.generate.repository.StylematerielRepository;
import com.package.generate.entity.Stylemateriel;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "stylemateriel")
public class StylematerielController
 {

	@Autowired
	private StylematerielRepository repository;


	@PostMapping()
	public ResponseEntity<Stylemateriel> save(@RequestBody Stylemateriel stylemateriel){
	 	return ResponseEntity.ok(repository.save(stylemateriel));
	}
	@PutMapping()
	public ResponseEntity<Stylemateriel> update(@RequestBody Stylemateriel stylemateriel){
	 	return ResponseEntity.ok(repository.save(stylemateriel));
	}
	@DeleteMapping()
	public void delete(@RequestBody Stylemateriel stylemateriel){
	 	return ResponseEntity.ok(repository.delete(stylemateriel));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Stylemateriel>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
