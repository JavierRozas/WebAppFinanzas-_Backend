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

import com.example.demo.entity.TipoDocumento;
import com.example.demo.services.ITipoDocumentoService;

@RestController
@RequestMapping("/api")
public class TipoDocumentoRestController {
	
	@Autowired
	private ITipoDocumentoService tipoDocumentoService;
	
	// Buscar Todos los TipoDocumentos:
	@GetMapping("/tipo_documentos")
	public List<TipoDocumento> index() {
		return tipoDocumentoService.findAll();
	}
	
	// Buscar por Id:
	@GetMapping("/tipo_documentos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TipoDocumento show(@PathVariable Long id) {
		return tipoDocumentoService.findById(id);
	}
	
	
	// Crear TipoDocumento:
	@PostMapping("/tipo_documentos")
	@ResponseStatus(HttpStatus.CREATED)
	public TipoDocumento create(@RequestBody TipoDocumento tipoDocumento) {
		return tipoDocumentoService.save(tipoDocumento);
	}
	
	// Eliminar TipoDocumento:
	@DeleteMapping("/tipo_documentos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		tipoDocumentoService.delete(id);
	}
	
}
