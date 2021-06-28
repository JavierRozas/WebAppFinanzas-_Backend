package com.example.demo.services;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dao.IDocumentoDao;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Descuentos;
import com.example.demo.entity.Documento;
import com.example.demo.entity.TipoDocumento;
import com.example.demo.entity.TipoMoneda;
import com.example.demo.entity.TipoTasa;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

	@Autowired
	private IDocumentoDao documentoDao;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IDescuentosService descuentoService;
	
	@Autowired
	private ITipoDocumentoService tipoDocumentoService;
	
	@Autowired
	private ITipoMonedaService tipoMonedaService;
	
	@Autowired
	private ITipoTasaService tipoTasaService;
	
	// Buscar Todos:
	@Override
	@Transactional(readOnly = true)
	public List<Documento> findAll() {
		return (List<Documento>) documentoDao.findAll();
	}

	// Buscar por Id:
	@Override
	@Transactional(readOnly = true)
	public Documento findbyId(Long Id) {
		return documentoDao.findById(Id).orElse(null);
	}

	// Crear Documento:
	@Override
	@Transactional
	public Documento save(Documento documento) {
		realizarCalculo(documento);
		return documentoDao.save(documento);
	}

	// Eliminar Documento:
	@Override
	@Transactional
	public void delete(Long Id) {
		documentoDao.deleteById(Id);
	}

	// Asignar un Cliente a un Documento:
	@Override
	public String assing(Long idDocumento, Long idCliente) {
		
		Cliente cliente = clienteService.findById(idCliente);
		Documento documento = findbyId(idDocumento);
		documento.setCliente(cliente);
		
		save(documento);
		
		return "Asignar - Funciona !!! :)";
	}

	// Asignar un TipoDocumento a un Documento:
	@Override
	public String assingTipo(Long idDocumento, Long idTipoDocumento) {
		
		TipoDocumento tipoDocumento = tipoDocumentoService.findById(idTipoDocumento);
		Documento documento = findbyId(idDocumento);
		documento.setTipoDocumento(tipoDocumento);
		
		save(documento);
		
		return "Asignar - Funciona !!! :)";
	}

	// Asignar un TipoMoneda a un Documento:
	@Override
	public String assingMoneda(Long idDocumento, Long idTipoMoneda) {
		
		TipoMoneda tipoMoneda = tipoMonedaService.findById(idTipoMoneda);
		Documento documento = findbyId(idDocumento);
		documento.setTipoMoneda(tipoMoneda);
		
		save(documento);
		
		return "Asignar - Funciona !!! :)";
	}

	// Asignar un TipoTasa a un Documento:
	@Override
	public String assingTasa(Long idDocumento, Long idTipoTasa) {
		
		TipoTasa tipoTasa = tipoTasaService.findById(idTipoTasa);
		Documento documento = findbyId(idDocumento);
		documento.setTipotasa(tipoTasa);
		
		save(documento);
		
		return "Asignar - Funciona !!! :)";
	}

	// ----------------------------------------------------------------
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoDocumento> findAllTipoDocumentos() {
		return documentoDao.findAllTipoDocumentos();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAllClientes() {
		return documentoDao.findAllClientes();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoMoneda> findAllMonedas() {
		return documentoDao.findAllMonedas();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoTasa> findAllTasa() {
		return documentoDao.findAllTasa();
	}

	// ********************* CALCULO *********************
	public void realizarCalculo(Documento documento) {
		
		// Crear nuevo descuento:
		Descuentos descuento = new Descuentos();
		descuento.setFechaDescuento(documento.getFechaDescuento());
		
		// Tasa Descontada:
		double tasa = (documento.getTasaEfectiva()/100) / (1 + (documento.getTasaEfectiva()/100));
		documento.setTasaDescontada(tasa * 100);
		
		// Descuento:
		double des = documento.getImporte() * (tasa);
		documento.setDescuentoNroDias((double) des);
		
		// Calcular valorTotalRecibir:
		double valorTotalRecibir_ = ((documento.getImporte() * (1 - tasa)) - documento.getRetencion());
		double valorTotalRecibir_gasto = valorTotalRecibir_ - documento.getGastoInicial();
		descuento.setValorTotalRecibir(valorTotalRecibir_gasto);
		
		// Calcular valorTotalEntregar:
		double valorTotalEntregar_ = (documento.getImporte() - documento.getRetencion());
		double valorTotalEntregar_gasto = valorTotalEntregar_ + documento.getGastoFinal();
		descuento.setValorTotalEntregar(valorTotalEntregar_gasto);
		
		// Calcular Numero de Dias:
		long diferencia = documento.getFechaVencimiento().getTime() - documento.getFechaDescuento().getTime();
		TimeUnit time = TimeUnit.DAYS;
		long diferenciaDias = time.convert(diferencia, TimeUnit.MILLISECONDS);
		
		int dias = (int) diferenciaDias;
		
		documento.setNroDiasTransaccion(dias);
		
		// Calcular TCEA:
		double TCEA_ = Math.pow((valorTotalEntregar_ / valorTotalRecibir_), (360 / dias)) - 1; 
		descuento.setTCEA(TCEA_ * 100);
		
		// Guardar Descuento:
		descuentoService.save(descuento);
	}

}
