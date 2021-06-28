package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "documentos")
public class Documento implements Serializable {

	// *** ATRIBUTOS ***
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date fechaGiro;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date fechaVencimiento;

	@Temporal(TemporalType.DATE)
	@NotNull
	private Date fechaDescuento;
	
	@Min(value = 1, message = "| El Importe tiene que ser un numero positivo mayor a 1")
	private double importe;
	
	@Min(value = 1, message = "| La Retencion tiene que ser un numero positivo mayor a 1")
	private double retencion;
	
	
	private int nroDiasTransaccion;
	
	@Min(value = 1, message = "| La Tasa Efectiva tiene que ser un numero positivo mayor a 1")
	private double tasaEfectiva;
	
	private double tasaDescontada;
	
	private double descuentoNroDias;
	
	@Min(value = 0, message = "| El Gasto Inicial tiene que ser un numero positivo mayor a 1")
	private double gastoInicial;
	
	@Min(value = 0, message = "| El Gasto Final tiene que ser un numero positivo mayor a 1")
	private double gastoFinal;
	
	// Clientes:
	@JsonIgnoreProperties({"documentos", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	// TipoDocumentos:
	@JsonIgnoreProperties({"documentos", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoDocumento_id")
	private TipoDocumento tipoDocumento;
	
	// TipoMonedas:
	@JsonIgnoreProperties({"documentos", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoMoneda_id")
	private TipoMoneda tipoMoneda;
	
	// TipoTasa:
	@JsonIgnoreProperties({"documentos", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoTasa_id")
	private TipoTasa tipotasa;
	
	// Descuentos:
	@JsonIgnoreProperties({"documento", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "documento", cascade = CascadeType.ALL)
	private List<Descuentos> descuentos;
	
	public Documento() {
		descuentos = new ArrayList<>();
	}
	
	// *** METODOS GET y SET ***
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaGiro() {
		return fechaGiro;
	}
	public void setFechaGiro(Date fechaGiro) {
		this.fechaGiro = fechaGiro;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public double getRetencion() {
		return retencion;
	}
	public void setRetencion(double retencion) {
		this.retencion = retencion;
	}
	public int getNroDiasTransaccion() {
		return nroDiasTransaccion;
	}
	public void setNroDiasTransaccion(int nroDiasTransaccion) {
		this.nroDiasTransaccion = nroDiasTransaccion;
	}
	public double getTasaEfectiva() {
		return tasaEfectiva;
	}
	public void setTasaEfectiva(double tasaEfectiva) {
		this.tasaEfectiva = tasaEfectiva;
	}
	public double getTasaDescontada() {
		return tasaDescontada;
	}
	public void setTasaDescontada(double tasaDescontada) {
		this.tasaDescontada = tasaDescontada;
	}
	public double getDescuentoNroDias() {
		return descuentoNroDias;
	}
	public void setDescuentoNroDias(double descuentoNroDias) {
		this.descuentoNroDias = descuentoNroDias;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Descuentos> getDescuentos() {
		return descuentos;
	}
	public void setDescuentos(List<Descuentos> descuentos) {
		this.descuentos = descuentos;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public TipoMoneda getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(TipoMoneda tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	public TipoTasa getTipotasa() {
		return tipotasa;
	}

	public void setTipotasa(TipoTasa tipotasa) {
		this.tipotasa = tipotasa;
	}
	public Date getFechaDescuento() {
		return fechaDescuento;
	}

	public void setFechaDescuento(Date fechaDescuento) {
		this.fechaDescuento = fechaDescuento;
	}

	public double getGastoInicial() {
		return gastoInicial;
	}

	public void setGastoInicial(double gastoInicial) {
		this.gastoInicial = gastoInicial;
	}

	public double getGastoFinal() {
		return gastoFinal;
	}

	public void setGastoFinal(double gastoFinal) {
		this.gastoFinal = gastoFinal;
	}




	private static final long serialVersionUID = 1L;
	
}
