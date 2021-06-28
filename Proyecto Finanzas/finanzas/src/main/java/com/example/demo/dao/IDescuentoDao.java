package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Descuentos;

public interface IDescuentoDao extends CrudRepository<Descuentos, Long> {

}
