package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Documento;
import com.example.demo.entity.TipoDocumento;
import com.example.demo.entity.TipoMoneda;
import com.example.demo.entity.TipoTasa;

public interface IDocumentoService {

	// Buscar Todos los Descuentos:
	public List<Documento> findAll();
	
	// Buscar por Id:
	public Documento findbyId(Long Id);
	
	// Crear Documento:
	public Documento save(Documento documento);
	
	// Eliminar Documento:
	public void delete(Long Id);
	
	// Asignar un Cliente a un Documento:
	public String assing(Long idDocumento, Long idCliente);
	
	// Asignar un TipoDocumento a un Documento:
	public String assingTipo(Long idDocumento, Long idTipoDocumento);
	
	// Asignar un TipoMoneda a un Documento:
	public String assingMoneda(Long idDocumento, Long idTipoMoneda);
	
	// Asignar un TipoTasa a un Documento:
	public String assingTasa(Long idDocumento, Long idTipoTasa);
	
	// ----------------------------
	
	public List<TipoDocumento> findAllTipoDocumentos();
	
	public List<Cliente> findAllClientes();
	
	public List<TipoMoneda> findAllMonedas();
	
	public List<TipoTasa> findAllTasa();

}
