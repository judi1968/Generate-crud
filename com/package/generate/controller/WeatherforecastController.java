package com.package.generate.controller;


import com.package.generate.repository.WeatherforecastRepository;
import com.package.generate.entity.Weatherforecast;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "weatherforecast")
public class WeatherforecastController
 {

	@Autowired
	private WeatherforecastRepository repository;


	@PostMapping()
	public ResponseEntity<Weatherforecast> save(@RequestBody Weatherforecast weatherforecast){
	 	return ResponseEntity.ok(repository.save(weatherforecast));
	}
	@PutMapping()
	public ResponseEntity<Weatherforecast> update(@RequestBody Weatherforecast weatherforecast){
	 	return ResponseEntity.ok(repository.save(weatherforecast));
	}
	@DeleteMapping()
	public void delete(@RequestBody Weatherforecast weatherforecast){
	 	return ResponseEntity.ok(repository.delete(weatherforecast));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Weatherforecast>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
