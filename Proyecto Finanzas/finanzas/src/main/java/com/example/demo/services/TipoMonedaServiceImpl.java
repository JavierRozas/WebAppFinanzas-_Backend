package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ITipoMonedaDao;
import com.example.demo.entity.TipoMoneda;

@Service
public class TipoMonedaServiceImpl implements ITipoMonedaService {

	@Autowired
	private ITipoMonedaDao tipoMonedaDao;
	
	// Buscar Todos los TipoMoneda:
	@Override
	@Transactional(readOnly = true)
	public List<TipoMoneda> findAll() {
		return (List<TipoMoneda>) tipoMonedaDao.findAll();
	}

	// Buscar por Id:
	@Override
	@Transactional(readOnly = true)
	public TipoMoneda findById(Long id) {
		return tipoMonedaDao.findById(id).orElse(null);
	}

	// Crear TipoMoneda:
	@Override
	@Transactional
	public TipoMoneda save(TipoMoneda tipoMoneda) {
		return tipoMonedaDao.save(tipoMoneda);
	}

	// Eliminar TipoMoneda:
	@Override
	@Transactional
	public void delete(Long id) {
		tipoMonedaDao.deleteById(id);
	}
	
}
