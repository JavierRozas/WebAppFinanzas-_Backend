package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Descuentos;
import com.example.demo.services.IDescuentosService;

@CrossOrigin(origins = "http://localhost:4200/", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/api")
public class DescuentosRestController {

	@Autowired
	private IDescuentosService descuentosService;
	
	// Buscar Todos
	@GetMapping("/descuentos")
	public List<Descuentos> index() {
		return descuentosService.findAll();
	}
	
	// Buscar por Id:
	@GetMapping("/descuentos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Descuentos show(@PathVariable Long id) {
		return descuentosService.findbyId(id);
	}
	
	// Crear Documento:
	@PostMapping("/descuentos")
	@ResponseStatus(HttpStatus.CREATED)
	public Descuentos crear(Descuentos descuento) {
		return descuentosService.save(descuento);
	}
	
	// Eliminar Documento:
	@DeleteMapping("/descuentos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		descuentosService.delete(id);
	}
	
	// Asignar un Cliente a un Documento:
	@GetMapping("/descuentos/assign/{idDocumento}/{idDescuento}")
	public String asignarDocumento(@PathVariable(value = "idDocumento") Long idDocumento, @PathVariable(value = "idDescuento") Long idDescuento) {
		return descuentosService.assing(idDocumento, idDescuento);
	}
	
}


