package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.TipoDocumento;

public interface ITipoDocumentoService {

	// Buscar Todos los TipoDocumentos:
	public List<TipoDocumento> findAll();
	
	// Buscar por Id:
	public TipoDocumento findById(Long id);
	
	// Crear TipoDocumento:
	public TipoDocumento save(TipoDocumento TipoDocumento);
	
	// Eliminar TipoDocumento:
	public void delete(Long id);
	
}
