package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.TipoMoneda;

public interface ITipoMonedaDao extends CrudRepository<TipoMoneda, Long> {

}
