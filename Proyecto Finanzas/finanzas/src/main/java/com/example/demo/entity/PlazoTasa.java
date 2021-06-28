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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "plazo_tasa")
public class PlazoTasa implements Serializable {

	// *** ATRIBUTOS ***
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@NotEmpty
	private String denominacion;
	
	@NotNull
	@Min(value = 1, message = "| El numero de dias tiene que ser un numero positivo mayor a 1")
	private int numeroDias;
	
	@JsonIgnoreProperties({"plazoTasa", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plazoTasa", cascade = CascadeType.ALL)
	private List<TipoTasa> tipoTasas;
	
	public PlazoTasa() {
		this.tipoTasas = new ArrayList<>();
	}
	
	
	// *** METODOS GET y SET ***
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public int getNumeroDias() {
		return numeroDias;
	}

	public void setNumeroDias(int numeroDias) {
		this.numeroDias = numeroDias;
	}
	
	public List<TipoTasa> getTipoTasas() {
		return tipoTasas;
	}

	public void setTipoTasas(List<TipoTasa> tipoTasas) {
		this.tipoTasas = tipoTasas;
	}


	private static final long serialVersionUID = 1L;

}
