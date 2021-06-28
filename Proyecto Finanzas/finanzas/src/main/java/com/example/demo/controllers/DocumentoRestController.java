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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Documento;
import com.example.demo.entity.PlazoTasa;
import com.example.demo.entity.TipoDocumento;
import com.example.demo.entity.TipoMoneda;
import com.example.demo.entity.TipoTasa;
import com.example.demo.services.IDocumentoService;

@CrossOrigin(origins = "http://localhost:4200/", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/api")
public class DocumentoRestController {

	@Autowired
	private IDocumentoService documentoService;
	
	// Buscar Todos
	@GetMapping("/documentos")
	public List<Documento> index() {
		return documentoService.findAll();
	}
	
	// Buscar por Id:
	@GetMapping("/documentos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Documento show(@PathVariable Long id) {
		return documentoService.findbyId(id);
	}
	
	// Crear
	@PostMapping("/documentos")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Documento documento, BindingResult result) {
		
		Documento documentoNew = null;
		Map<String, Object> response = new HashMap();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "Error en el campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		documentoNew = documentoService.save(documento);
		response.put("mensaje", "El Plazo Tasa ha sido creado con éxito!");
		response.put("plazoTasa", documentoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	// Eliminar Documento:
	@DeleteMapping("/documento/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		documentoService.delete(id);
	}
	
	// Actualizar
	@PutMapping("/documentos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Documento update(@RequestBody Documento documento, @PathVariable Long id) {
		Documento documentoActual = documentoService.findbyId(id);
		
		//documentoActual.setDescuentoNroDias(documento.getDescuentoNroDias());
		documentoActual.setFechaGiro(documento.getFechaGiro());
		documentoActual.setFechaVencimiento(documento.getFechaVencimiento());
		documentoActual.setImporte(documento.getImporte());
		documentoActual.setNroDiasTransaccion(documento.getNroDiasTransaccion());
		documentoActual.setRetencion(documento.getRetencion());
		documentoActual.setTasaDescontada(documento.getTasaDescontada());
		documentoActual.setTasaEfectiva(documento.getTasaEfectiva());
		
		// Tipo Documento:
		documentoActual.setTipoDocumento(documento.getTipoDocumento());

		
		return documentoService.save(documentoActual);
	}
	
	// Asignar un Cliente a un Documento: 
	@GetMapping("/documento/assign/{idDocumento}/{idCliente}")
	public String asignarCliente(@PathVariable(value = "idDocumento") Long idDocumento, @PathVariable(value = "idCliente") Long idCliente) {
		return documentoService.assing(idDocumento, idCliente);
	}
	
	// Asignar un TipoDocumento a un Documento:
	@GetMapping("/tipo_documento/assign/{idDocumento}/{idTipoDocumento}")
	public String asignarTipoDocumento(@PathVariable(value = "idDocumento") Long idDocumento, @PathVariable(value = "idTipoDocumento") Long idTipoDocumento) {
		return documentoService.assingTipo(idDocumento, idTipoDocumento);
	}
	
	// Asignar un TipoMoneda a un Documento:
	@GetMapping("/tipo_moneda/assign/{idDocumento}/{idTipoMoneda}")
	public String asignarTipoMoneda(@PathVariable(value = "idDocumento") Long idDocumento, @PathVariable(value = "idTipoMoneda") Long idTipoMoneda) {
		return documentoService.assingMoneda(idDocumento, idTipoMoneda);
	}
	
	// Asignar un TipoTasa a un Documento:
	@GetMapping("/documentos/tasa/{idDocumento}/{idTipoTasa}")
	public String asignarTipoTasa(@PathVariable(value = "idDocumento") Long idDocumento, @PathVariable(value = "idTipoTasa") Long idTipoTasa) {
		return documentoService.assingTasa(idDocumento, idTipoTasa);
	}
	
	// ----------------------------------------------------------------
	
	@GetMapping("/documentos/tipo_documentos")
	public List<TipoDocumento> listarTipoDocumentos() {
		return documentoService.findAllTipoDocumentos();
	}
	
	@GetMapping("/documentos/clientes")
	public List<Cliente> listarClientes() {
		return documentoService.findAllClientes();
	}
	
	@GetMapping("/documentos/monedas")
	public List<TipoMoneda> listarMonedas() {
		return documentoService.findAllMonedas();
	}
	
	@GetMapping("/documentos/tasas")
	public List<TipoTasa> listarTasas() {
		return documentoService.findAllTasa();
	}
	
}


