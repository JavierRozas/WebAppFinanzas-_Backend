package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "descuentos")
public class Descuentos implements Serializable {

	// *** ATRIBUTOS ***
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date fechaDescuento;
	
	private double valorTotalRecibir;
	private double valorTotalEntregar;
	private double TCEA;
	
	@JsonIgnoreProperties({"descuentos", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "documento_id")
	private Documento documento;
	
	// *** METODOS GET y SET ***
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaDescuento() {
		return fechaDescuento;
	}

	public void setFechaDescuento(Date fechaDescuento) {
		this.fechaDescuento = fechaDescuento;
	}

	public double getValorTotalRecibir() {
		return valorTotalRecibir;
	}

	public void setValorTotalRecibir(double valorTotalRecibir) {
		this.valorTotalRecibir = valorTotalRecibir;
	}

	public double getValorTotalEntregar() {
		return valorTotalEntregar;
	}

	public void setValorTotalEntregar(double valorTotalEntregar) {
		this.valorTotalEntregar = valorTotalEntregar;
	}

	public double getTCEA() {
		return TCEA;
	}

	public void setTCEA(double tCEA) {
		TCEA = tCEA;
	}
	
	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}



	private static final long serialVersionUID = 1L;
}
