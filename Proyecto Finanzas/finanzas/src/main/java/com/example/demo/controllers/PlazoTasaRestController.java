package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Documento;
import com.example.demo.entity.PlazoTasa;
import com.example.demo.services.IPlazoTasaService;

@CrossOrigin(origins = "http://localhost:4200/", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/api")
public class PlazoTasaRestController {

	@Autowired
	private IPlazoTasaService plazoTasaService;
	
	// Buscar Todos los PlazoTasa:
	@GetMapping("/plazo_tasas")
	public List<PlazoTasa> index() {
		return plazoTasaService.findAll();
	}
	
	// Buscar por Id:
	@GetMapping("/plazo_tasas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PlazoTasa show(@PathVariable Long id) {
		return plazoTasaService.findById(id);
	}
	
	// Crear
	@PostMapping("/plazo_tasas")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody PlazoTasa plazoTasa, BindingResult result) {
		
		PlazoTasa plazoTasaNew = null;
		Map<String, Object> response = new HashMap();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "Error en el campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		plazoTasaNew = plazoTasaService.save(plazoTasa);
		response.put("mensaje", "El Plazo Tasa ha sido creado con éxito!");
		response.put("plazoTasa", plazoTasaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	// Eliminar PlazoTasa:
	@DeleteMapping("/plazo_tasas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		plazoTasaService.delete(id);
	}
	
}
