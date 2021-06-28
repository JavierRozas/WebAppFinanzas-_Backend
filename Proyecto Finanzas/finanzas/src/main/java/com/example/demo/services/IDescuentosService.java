package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Descuentos;

public interface IDescuentosService {
	
	// Buscar Todos los Descuentos:
	public List<Descuentos> findAll();
	
	// Buscar por Id:
	public Descuentos findbyId(Long Id);
	
	// Crear Descuento:
	public Descuentos save(Descuentos descuentos);
	
	// Eliminar Descuento:
	public void delete(Long Id);
	
	// Asignar un Descuento a un Documento:
	public String assing(Long idDocumento, Long idDescuento); 

}
