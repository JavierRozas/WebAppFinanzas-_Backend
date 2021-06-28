package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Documento;
import com.example.demo.entity.TipoDocumento;
import com.example.demo.entity.TipoMoneda;
import com.example.demo.entity.TipoTasa;


public interface IDocumentoDao extends CrudRepository<Documento, Long> {
	
	@Query("from TipoDocumento")
	public List<TipoDocumento> findAllTipoDocumentos();
	
	@Query("from Cliente")
	public List<Cliente> findAllClientes();
	
	@Query("from TipoMoneda")
	public List<TipoMoneda> findAllMonedas();
	
	@Query("from TipoTasa")
	public List<TipoTasa> findAllTasa();
	
}
