package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ITipoTasaDao;
import com.example.demo.entity.PlazoTasa;
import com.example.demo.entity.TipoTasa;

@Service
public class TipoTasaServiceImpl implements ITipoTasaService {

	@Autowired
	private ITipoTasaDao tipoTasaDao;
	
	@Autowired 
	private IPlazoTasaService plazoTasaService;
	
	// Buscar Todos los TipoTasa:
	@Override
	@Transactional(readOnly = true)
	public List<TipoTasa> findAll() {
		return (List<TipoTasa>) tipoTasaDao.findAll();
	}

	// Buscar por Id:
	@Override
	@Transactional(readOnly = true)
	public TipoTasa findById(Long id) {
		return tipoTasaDao.findById(id).orElse(null);
	}
	
	// Crear TipoTasa:
	@Override
	@Transactional
	public TipoTasa save(TipoTasa tipoTasa) {
		return tipoTasaDao.save(tipoTasa);
	}

	// Eliminar TipoTasa:
	@Override
	@Transactional
	public void delete(Long id) {
		tipoTasaDao.deleteById(id);
	}

	// Asignar un PlazoTasa a un TipoTasa:
	@Override
	public String assingPlazo(Long idTipoTasa, Long idPlazoTasa) {
		
		PlazoTasa plazoTasa = plazoTasaService.findById(idPlazoTasa);
		TipoTasa tipoTasa = findById(idTipoTasa);
		tipoTasa.setPlazoTasa(plazoTasa);
		
		save(tipoTasa);
		
		return "Asignar - Funciona !!! :)";
	}

	// ----------------------------------------------------------------
	
	@Override
	@Transactional(readOnly = true)
	public List<PlazoTasa> findAllPlazos() {
		return tipoTasaDao.findAllPlazos();
	}

}
