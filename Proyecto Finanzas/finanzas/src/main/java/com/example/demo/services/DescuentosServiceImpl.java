package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IDescuentoDao;
import com.example.demo.entity.Descuentos;
import com.example.demo.entity.Documento;

@Service
public class DescuentosServiceImpl implements IDescuentosService {

	@Autowired
	private IDescuentoDao descuentoDao;
	
	@Autowired
	private IDocumentoService documentoService;
	
	// Buscar Todos:
	@Override
	@Transactional(readOnly = true)
	public List<Descuentos> findAll() {
		return (List<Descuentos>) descuentoDao.findAll();
	}

	// Buscar por Id:
	@Override
	@Transactional(readOnly = true)
	public Descuentos findbyId(Long Id) {
		return descuentoDao.findById(Id).orElse(null);
	}

	// Crear Descuento:
	@Override
	@Transactional
	public Descuentos save(Descuentos descuentos) {
		return descuentoDao.save(descuentos);
	}

	// Eliminar Descuento:
	@Override
	@Transactional
	public void delete(Long Id) {
		descuentoDao.deleteById(Id);
	}

	// Asignar un Descuento a un Documento:
	@Override
	public String assing(Long idDocumento, Long idDescuento) {
		
		Documento documento = documentoService.findbyId(idDocumento);
		Descuentos descuento = findbyId(idDescuento);
		descuento.setDocumento(documento);
		
		save(descuento);
		
		return "Asignar - Funciona !!! :)";
	}

}
