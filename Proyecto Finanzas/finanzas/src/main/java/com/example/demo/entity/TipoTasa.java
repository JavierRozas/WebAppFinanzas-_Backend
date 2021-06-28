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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tipo_tasa")
public class TipoTasa implements Serializable {
	
	// *** ATRIBUTOS ***
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Min(value = 1, message = "| El porcentaje tiene que ser un numero positivo mayor a 1")
	@Max(value = 100, message = "| El porcentaje tiene que ser un numero positivo menor a 100")
	private float porcentaje;
	
	@NotNull
	private String nombreTasa;
	
	@Min(value = 360, message = "| El numero de días por año solo puede tomar los valores 360 dás o 365 días")
	@Max(value = 365, message = "| El numero de días por año solo puede tomar los valores 360 dás o 365 días")
	private int diasAnio;
	
	// PlazoTasa:
	@JsonIgnoreProperties({"tipoTasas", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plazoTasa_id")
	private PlazoTasa plazoTasa;
	
	@JsonIgnoreProperties({"tipotasa", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipotasa", cascade = CascadeType.ALL)
	private List<Documento> documentos;
	
	public TipoTasa() {
		this.documentos = new ArrayList<>();
	}
	
	
	// *** METODOS GET y SET ***
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getNombreTasa() {
		return nombreTasa;
	}

	public void setNombreTasa(String nombreTasa) {
		this.nombreTasa = nombreTasa;
	}

	public int getDiasAnio() {
		return diasAnio;
	}

	public void setDiasAnio(int diasAnio) {
		this.diasAnio = diasAnio;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public PlazoTasa getPlazoTasa() {
		return plazoTasa;
	}

	public void setPlazoTasa(PlazoTasa plazoTasa) {
		this.plazoTasa = plazoTasa;
	}


	private static final long serialVersionUID = 1L;

}
