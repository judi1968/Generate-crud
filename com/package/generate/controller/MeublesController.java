package com.package.generate.controller;


import com.package.generate.repository.MeublesRepository;
import com.package.generate.entity.Meubles;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "meubles")
public class MeublesController
 {

	@Autowired
	private MeublesRepository repository;


	@PostMapping()
	public ResponseEntity<Meubles> save(@RequestBody Meubles meubles){
	 	return ResponseEntity.ok(repository.save(meubles));
	}
	@PutMapping()
	public ResponseEntity<Meubles> update(@RequestBody Meubles meubles){
	 	return ResponseEntity.ok(repository.save(meubles));
	}
	@DeleteMapping()
	public void delete(@RequestBody Meubles meubles){
	 	return ResponseEntity.ok(repository.delete(meubles));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Meubles>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
