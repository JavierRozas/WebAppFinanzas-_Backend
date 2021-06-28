package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.PlazoTasa;

public interface IPlazoTasaService {

	// Buscar Todos los PlazoTasa:
	public List<PlazoTasa> findAll();
	
	// Buscar por Id:
	public PlazoTasa findById(Long id);
	
	// Crear PlazoTasa:
	public PlazoTasa save(PlazoTasa plazoTasa);
	
	// Eliminar PlazoTasa:
	public void delete(Long id);
	
}
