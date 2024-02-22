package com.package.generate.controller;


import com.package.generate.repository.MeublecomposantRepository;
import com.package.generate.entity.Meublecomposant;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "meublecomposant")
public class MeublecomposantController
 {

	@Autowired
	private MeublecomposantRepository repository;


	@PostMapping()
	public ResponseEntity<Meublecomposant> save(@RequestBody Meublecomposant meublecomposant){
	 	return ResponseEntity.ok(repository.save(meublecomposant));
	}
	@PutMapping()
	public ResponseEntity<Meublecomposant> update(@RequestBody Meublecomposant meublecomposant){
	 	return ResponseEntity.ok(repository.save(meublecomposant));
	}
	@DeleteMapping()
	public void delete(@RequestBody Meublecomposant meublecomposant){
	 	return ResponseEntity.ok(repository.delete(meublecomposant));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Meublecomposant>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
