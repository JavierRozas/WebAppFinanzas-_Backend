package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.TipoDocumento;

public interface ITipoDocumentoDao extends CrudRepository<TipoDocumento, Long> {

}
