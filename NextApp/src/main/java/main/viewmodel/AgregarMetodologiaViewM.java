package main.viewmodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import main.app.AplicacionContexto;
import main.condiciones.Condicion;
import main.condiciones.CondicionesBuilder;
import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioMetodologia;
import model.Metodologia;
import model.SnapshotCondicion;

import org.uqbar.commons.utils.Observable;

@Observable
public class AgregarMetodologiaViewM {

	/********* ATRIBUTOS *********/

	private String nombre;
	private BigDecimal pesoOComparar = null;
	private List<String> tiposCondiciones = new ArrayList<String>();
	private String tipoCondicionSeleccionado;
	private Integer agregarPrioridadSeleccionada;
	private List<String> agregarIndicador;
	private String agregarIndicadorSeleccionado;
	private List<String> agregarCriterio = new ArrayList<String>();
	private String agregarCriterioSeleccionado;
	private List<Integer> agregarAnios = new ArrayList<Integer>();
	private Integer agregarAniosSeleccionado;
	private List<SnapshotCondicion> snapshotCondiciones = new ArrayList<SnapshotCondicion>();;
	private SnapshotCondicion snapshotCondicionSeleccionado;

	/********* GETTERS/SETTERS *********/

	public String getNombre() {
		return nombre;
	}

	public List<Integer> getAgregarAnios() {
		return agregarAnios;
	}

	public void setAgregarAnios(List<Integer> agregarAnios) {
		this.agregarAnios = agregarAnios;
	}

	public Integer getAgregarAniosSeleccionado() {
		return agregarAniosSeleccionado;
	}

	public void setAgregarAniosSeleccionado(Integer agregarAniosSeleccionado) {
		this.agregarAniosSeleccionado = agregarAniosSeleccionado;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getAgregarIndicador() {
		return agregarIndicador;
	}

	public void setAgregarIndicador(List<String> agregarIndicador) {
		this.agregarIndicador = agregarIndicador;
	}

	public String getAgregarIndicadorSeleccionado() {
		return agregarIndicadorSeleccionado;
	}

	public void setAgregarIndicadorSeleccionado(String agregarIndicadorSeleccionado) {
		this.agregarIndicadorSeleccionado = agregarIndicadorSeleccionado;
	}

	public List<String> getAgregarCriterio() {
		return agregarCriterio;
	}

	public void setAgregarCriterio(List<String> agregarCriterio) {
		this.agregarCriterio = agregarCriterio;
	}

	public String getAgregarCriterioSeleccionado() {
		return agregarCriterioSeleccionado;
	}

	public void setAgregarCriterioSeleccionado(String agregarCriterioSeleccionado) {

		if (agregarCriterioSeleccionado.equalsIgnoreCase(CondicionesBuilder.ANTIGUEDAD)) {
			this.setAgregarIndicadorSeleccionado(null);
			this.setAgregarIndicador(null);
		} else {
			this.cargarIndicadoresDisponibles();
		}

		this.agregarCriterioSeleccionado = agregarCriterioSeleccionado;
	}

	public Integer getAgregarPrioridadSeleccionada() {
		return agregarPrioridadSeleccionada;
	}

	public void setAgregarPrioridadSeleccionada(Integer agregarPrioridadSeleccionado) {
		this.agregarPrioridadSeleccionada = agregarPrioridadSeleccionado;
	}

	public List<SnapshotCondicion> getSnapshotCondiciones() {
		return snapshotCondiciones;
	}

	public void setSnapshotCondiciones(List<SnapshotCondicion> snapshotCondiciones) {
		this.snapshotCondiciones = snapshotCondiciones;
	}

	public SnapshotCondicion getSnapshotCondicionSeleccionado() {
		return snapshotCondicionSeleccionado;
	}

	public void setSnapshotCondicionSeleccionado(SnapshotCondicion snapshotCondicionSeleccionado) {
		this.snapshotCondicionSeleccionado = snapshotCondicionSeleccionado;
	}

	public BigDecimal getPesoOComparar() {
		return pesoOComparar;
	}

	public void setPesoOComparar(BigDecimal pesoOComparar) {
		this.pesoOComparar = pesoOComparar;
	}

	public List<String> getTiposCondiciones() {
		return tiposCondiciones;
	}

	public void setTiposCondiciones(List<String> tiposCondiciones) {
		this.tiposCondiciones = tiposCondiciones;
	}

	public String getTipoCondicionSeleccionado() {
		return tipoCondicionSeleccionado;
	}

	public void setTipoCondicionSeleccionado(String tiposCondicionSeleccionado) {
		this.tipoCondicionSeleccionado = tiposCondicionSeleccionado;
	}

	/********* METODOS *********/

	public AgregarMetodologiaViewM() {
		this.cargarIndicadoresDisponibles();
		this.cargarTiposDeCondiciones();
		this.cargarCriteriosDisponibles();
		this.cargarAniosDisponibles();
	}

	private void cargarAniosDisponibles() {
		this.agregarAnios.add(1);
		this.agregarAnios.add(2);
		this.agregarAnios.add(3);
		this.agregarAnios.add(4);
		this.agregarAnios.add(5);
		this.agregarAnios.add(10);
		this.agregarAnios.add(15);
		this.agregarAnios.add(20);
		this.agregarAnios.add(30);
		this.agregarAnios.add(50);
	}

	private void cargarCriteriosDisponibles() {
		this.agregarCriterio.add(CondicionesBuilder.MAYOR);
		this.agregarCriterio.add(CondicionesBuilder.MENOR);
		this.agregarCriterio.add(CondicionesBuilder.ANTIGUEDAD);
		this.agregarCriterio.add(CondicionesBuilder.CRECIENTE);
		this.agregarCriterio.add(CondicionesBuilder.DECRECIENTE);
	}

	private void cargarTiposDeCondiciones() {
		this.tiposCondiciones.add(CondicionesBuilder.TAXATIVA);
		this.tiposCondiciones.add(CondicionesBuilder.CUANTITATIVA);
	}

	private void cargarIndicadoresDisponibles() {
		this.agregarIndicador = this.getRepositorindicadores()
				.todosLosNombresDeIndicadores(this.getRepositorindicadores().allInstances());
	}

	public void guardarMetodologia() {

		if (this.getNombre() == null || (this.getSnapshotCondiciones().isEmpty()))
			throw new RuntimeException("Debe ingresar nombre y al menos una condicion para guardar correctamente. "
					+ "Intentelo nuevamente");
		List<Condicion> condiciones = new ArrayList<Condicion>();

		this.getSnapshotCondiciones()
				.forEach(snapshotCondicion -> condiciones.add(new CondicionesBuilder().crear(snapshotCondicion)));
		Metodologia metodologia = new Metodologia(this.getNombre(), condiciones);
		this.getRepositorioMetodologias().agregar(metodologia);

	}

	public void agregarCondicion() {
		String indicadorSeleccionado;
		if (this.getAgregarIndicadorSeleccionado() == null) {
			indicadorSeleccionado = CondicionesBuilder.VACIARINDICADOR;
		} else {
			indicadorSeleccionado = this.getAgregarIndicadorSeleccionado();
		}

		this.snapshotCondiciones
				.add(new SnapshotCondicion(this.getTipoCondicionSeleccionado(), this.getAgregarCriterioSeleccionado(),
						indicadorSeleccionado, this.getPesoOComparar(), this.getAgregarAniosSeleccionado()));
		this.limpiar();
	}

	public void limpiar() {
		// con el set me tira error
		this.agregarCriterioSeleccionado = null;
		this.setPesoOComparar(BigDecimal.ZERO);
		this.setTipoCondicionSeleccionado(null);
		this.setAgregarAniosSeleccionado(null);
		this.setAgregarIndicadorSeleccionado(null);
	}

	public void validar() throws RuntimeException {
		if (this.getAgregarIndicadorSeleccionado() == null
				&& !this.getAgregarCriterioSeleccionado().equalsIgnoreCase(CondicionesBuilder.ANTIGUEDAD)) {
			throw new RuntimeException("Seleccione un indicador");
		}

		if (this.getTipoCondicionSeleccionado() == null) {
			throw new RuntimeException("Seleccione un tipo de condicion");
		}
		if (this.getAgregarCriterioSeleccionado() == null) {
			throw new RuntimeException("Seleccione una condicion");
		}
		if (this.getTipoCondicionSeleccionado().equalsIgnoreCase(CondicionesBuilder.CUANTITATIVA)
				&& (this.getAgregarCriterioSeleccionado().equalsIgnoreCase(CondicionesBuilder.CRECIENTE)
						|| this.getAgregarCriterioSeleccionado().equalsIgnoreCase(CondicionesBuilder.DECRECIENTE))) {
			throw new RuntimeException("Las condiciones Creciente y Decreciente no pueden ser Cuantitativas");
		}
		if (!this.getAgregarCriterioSeleccionado().equalsIgnoreCase(CondicionesBuilder.ANTIGUEDAD)
				&& this.getAgregarAniosSeleccionado() == null) {
			throw new RuntimeException("Falta indicar desde que año aplica la condicion");
		} else {
			if (this.getAgregarAniosSeleccionado() == null) {
				this.setAgregarAniosSeleccionado(0);
			}
		}
		if (!this.getAgregarCriterioSeleccionado().equalsIgnoreCase(CondicionesBuilder.CRECIENTE)
				&& !this.getAgregarCriterioSeleccionado().equalsIgnoreCase(CondicionesBuilder.DECRECIENTE)
				&& this.getPesoOComparar() == null) {
			throw new RuntimeException("Falta indicar el Peso o Numero a Comparar");
		} else {
			if (this.getPesoOComparar() == null) {
				this.setPesoOComparar(BigDecimal.ZERO);
			}
		}
	}

	public RepositorioIndicador getRepositorindicadores() {
		return AplicacionContexto.getInstance().getInstanceRepoIndicadores();
	}

	public RepositorioMetodologia getRepositorioMetodologias() {
		return AplicacionContexto.getInstance().getInstanceRepoMetodologias();
	}

}
