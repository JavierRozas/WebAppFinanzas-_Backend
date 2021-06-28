package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.PlazoTasa;
import com.example.demo.entity.TipoTasa;

public interface ITipoTasaDao extends CrudRepository<TipoTasa, Long> {

	@Query("from PlazoTasa")
	public List<PlazoTasa> findAllPlazos();
}
