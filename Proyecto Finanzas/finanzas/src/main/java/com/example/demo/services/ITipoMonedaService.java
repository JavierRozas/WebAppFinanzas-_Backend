package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.TipoMoneda;

public interface ITipoMonedaService {

	// Buscar Todos los TipoMoneda:
	public List<TipoMoneda> findAll();
	
	// Buscar por Id:
	public TipoMoneda findById(Long id);
	
	// Crear TipoMoneda:
	public TipoMoneda save(TipoMoneda tipoMoneda);
	
	// Eliminar TipoMoneda:
	public void delete(Long id);
	
}
