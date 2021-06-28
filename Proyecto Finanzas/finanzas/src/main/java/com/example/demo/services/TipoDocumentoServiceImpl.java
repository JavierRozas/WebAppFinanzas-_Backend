package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ITipoDocumentoDao;
import com.example.demo.entity.TipoDocumento;

@Service
public class TipoDocumentoServiceImpl implements ITipoDocumentoService {

	@Autowired
	private ITipoDocumentoDao tipoDocumentoDao;
	
	// Buscar Todos los TipoDocumentos:
	@Override
	@Transactional(readOnly = true)
	public List<TipoDocumento> findAll() {
		return (List<TipoDocumento>) tipoDocumentoDao.findAll();
	}

	// Buscar por Id:
	@Override
	@Transactional(readOnly = true)
	public TipoDocumento findById(Long id) {
		return tipoDocumentoDao.findById(id).orElse(null);
	}

	// Crear TipoDocumento:
	@Override
	@Transactional
	public TipoDocumento save(TipoDocumento TipoDocumento) {
		return tipoDocumentoDao.save(TipoDocumento);
	}

	// Eliminar TipoDocumento:
	@Override
	@Transactional
	public void delete(Long id) {
		tipoDocumentoDao.deleteById(id);
	}
	
}
