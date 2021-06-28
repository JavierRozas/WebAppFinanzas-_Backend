package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.PlazoTasa;
import com.example.demo.entity.TipoTasa;

public interface ITipoTasaService {
	
	// Buscar Todos los TipoTasa:
	public List<TipoTasa> findAll();
	
	// Buscar por Id:
	public TipoTasa findById(Long id);
	
	// Crear TipoTasa:
	public TipoTasa save(TipoTasa tipoTasa);
	
	// Eliminar TipoTasa:
	public void delete(Long id);

	// Asignar un PlazoTasa a un TipoTasa:
	public String assingPlazo(Long idTipoTasa, Long idPlazoTasa);
	
	// ----------------------------
	
	public List<PlazoTasa> findAllPlazos();
	
}
