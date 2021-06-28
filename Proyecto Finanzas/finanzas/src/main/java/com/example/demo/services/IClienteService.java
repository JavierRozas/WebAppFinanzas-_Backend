package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Cliente;

public interface IClienteService {

	// Buscar Todos los clientes:
	public List<Cliente> findAll();
	
	// Agregar
	public Cliente save(Cliente cliente);
	
	// Eliminar
	public void delete(Long id);
	
	// Buscar -> por ID
	public Cliente findById(Long id);
}
