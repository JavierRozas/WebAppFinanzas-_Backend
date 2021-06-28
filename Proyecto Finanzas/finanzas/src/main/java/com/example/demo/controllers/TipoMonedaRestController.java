package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TipoMoneda;
import com.example.demo.services.ITipoMonedaService;

@RestController
@RequestMapping("/api")
public class TipoMonedaRestController {
	
	@Autowired
	private ITipoMonedaService tipoMonedaService;
	
	// Buscar Todos los TipoMoneda:
	@GetMapping("/tipo_monedas")
	public List<TipoMoneda> index() {
		return tipoMonedaService.findAll();
	}
	
	// Buscar por Id:
	@GetMapping("/tipo_monedas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TipoMoneda show(@PathVariable Long id) {
		return tipoMonedaService.findById(id);
	}

	// Crear TipoMoneda:
	@PostMapping("/tipo_monedas")
	@ResponseStatus(HttpStatus.CREATED)
	public TipoMoneda create(@RequestBody TipoMoneda tipoMoneda) {
		return tipoMonedaService.save(tipoMoneda);
	}
	
	// Eliminar TipoMoneda:
	@DeleteMapping("/tipo_monedas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		tipoMonedaService.delete(id);
	}
	
}
