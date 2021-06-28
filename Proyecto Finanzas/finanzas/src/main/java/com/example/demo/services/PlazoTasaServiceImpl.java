package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IPlazoTasaDao;
import com.example.demo.entity.PlazoTasa;

@Service
public class PlazoTasaServiceImpl implements IPlazoTasaService {

	@Autowired
	private IPlazoTasaDao plazoTasaDao;
	
	// Buscar Todos los PlazoTasa:
	@Override
	@Transactional(readOnly = true)
	public List<PlazoTasa> findAll() {
		return (List<PlazoTasa>) plazoTasaDao.findAll();
	}

	// Buscar por Id:
	@Override
	@Transactional(readOnly = true)
	public PlazoTasa findById(Long id) {
		return plazoTasaDao.findById(id).orElse(null);
 	}

	// Crear PlazoTasa:
	@Override
	@Transactional
	public PlazoTasa save(PlazoTasa plazoTasa) {
		return plazoTasaDao.save(plazoTasa);
	}

	// Eliminar PlazoTasa:
	@Override
	@Transactional
	public void delete(Long id) {
		plazoTasaDao.deleteById(id);
	}

}
