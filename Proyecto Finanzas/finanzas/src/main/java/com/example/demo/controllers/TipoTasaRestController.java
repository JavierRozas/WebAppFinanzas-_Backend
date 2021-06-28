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

import com.example.demo.entity.PlazoTasa;
import com.example.demo.entity.TipoTasa;
import com.example.demo.services.ITipoTasaService;

@CrossOrigin(origins = "http://localhost:4200/", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/api")
public class TipoTasaRestController {
	
	@Autowired
	private ITipoTasaService tipoTasaService;
	
	// Buscar Todos los TipoTasa:
	@GetMapping("/tipo_tasas")
	public List<TipoTasa> index() {
		return tipoTasaService.findAll();
	}
	
	// Buscar por Id:
	@GetMapping("/tipo_tasas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TipoTasa show(@PathVariable Long id) {
		return tipoTasaService.findById(id);
	}
	
	// Crear
	@PostMapping("/tipo_tasas")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody TipoTasa tipoTasa, BindingResult result) {
		
		TipoTasa tipoTasaNew = null;
		Map<String, Object> response = new HashMap();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "Error en el campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		tipoTasaNew = tipoTasaService.save(tipoTasa);
		response.put("mensaje", "El Plazo Tasa ha sido creado con éxito!");
		response.put("tipoTasa", tipoTasaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	// Eliminar TipoTasa:
	@DeleteMapping("/tipo_tasas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		tipoTasaService.delete(id);
	}
	
	// Asignar un PlazoTasa a un TipoTasa: 
	@GetMapping("/tipo_tasas/assign/{idTipoTasa}/{idPlazoTasa}")
	public String asignarPlazo(@PathVariable(value = "idTipoTasa") Long idTipoTasa, @PathVariable(value = "idPlazoTasa") Long idPlazoTasa) {
		return tipoTasaService.assingPlazo(idTipoTasa, idPlazoTasa);
	}
	
	// ----------------------------------------------------------------
	
	@GetMapping("/tipo_tasas/plazos")
	public List<PlazoTasa> listarPlazos() {
		return tipoTasaService.findAllPlazos();
	}
	
}
