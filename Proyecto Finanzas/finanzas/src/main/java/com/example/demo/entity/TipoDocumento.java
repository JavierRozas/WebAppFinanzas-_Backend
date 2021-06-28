package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tipo_documento")
public class TipoDocumento implements Serializable {
	
	// *** ATRIBUTOS ***
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String nombreDocumento;
	
	@JsonIgnoreProperties({"tipoDocumento", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoDocumento", cascade = CascadeType.ALL)
	private List<Documento> documentos;
	
	public TipoDocumento() {
		this.documentos = new ArrayList<>();
	}
	
	// *** METODOS GET y SET ***
	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getNombreDocumento() {
		return nombreDocumento;
	}


	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	
	public List<Documento> getDocumentos() {
		return documentos;
	}


	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}



	private static final long serialVersionUID = 1L;

	
}
