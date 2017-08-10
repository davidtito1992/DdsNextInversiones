package viewmodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.Metodologia;
import model.SnapshotCondicion;

import org.uqbar.commons.utils.Observable;

import repositories.RepositorioUnicoDeIndicadores;
import repositories.RepositorioUnicoDeMetodologias;
import app.AplicacionContexto;
import condiciones.Condicion;
import condiciones.CondicionesBuilder;

@Observable
public class AgregarMetodologiaViewM {

	/********* ATRIBUTOS *********/

	private String nombre;
	private Long pesoOComparar = null;
	private List<String> tiposCondiciones = new ArrayList<String>();
	private String tipoCondicionSeleccionado;
	private Integer agregarPrioridadSeleccionada;
	private List<String> agregarIndicador;
	private String agregarIndicadorSeleccionado;
	private List<String> agregarCriterio = new ArrayList<String>();
	private String agregarCriterioSeleccionado;
	private List<BigDecimal> agregarNro = new ArrayList<BigDecimal>();
	private BigDecimal agregarNroSeleccionado;
	private List<Integer> agregarAnios = new ArrayList<Integer>();
	private Integer agregarAniosSeleccionado;
	private List<SnapshotCondicion> snapshotCondiciones = new ArrayList<SnapshotCondicion>();;
	private SnapshotCondicion snapshotCondicionSeleccionado;

	/********* GETTERS/SETTERS *********/

	public String getNombre() {
		return nombre;
	}

	public List<BigDecimal> getAgregarNro() {
		return agregarNro;
	}

	public void setAgregarNro(List<BigDecimal> agregarNro) {
		this.agregarNro = agregarNro;
	}

	public BigDecimal getAgregarNroSeleccionado() {
		return agregarNroSeleccionado;
	}

	public void setAgregarNroSeleccionado(BigDecimal agregarNroSeleccionado) {
		this.agregarNroSeleccionado = agregarNroSeleccionado;
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

	public void setAgregarIndicadorSeleccionado(
			String agregarIndicadorSeleccionado) {
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

	public void setAgregarCriterioSeleccionado(
			String agregarCriterioSeleccionado) {
		this.agregarCriterioSeleccionado = agregarCriterioSeleccionado;
	}

	public Integer getAgregarPrioridadSeleccionada() {
		return agregarPrioridadSeleccionada;
	}

	public void setAgregarPrioridadSeleccionada(
			Integer agregarPrioridadSeleccionado) {
		this.agregarPrioridadSeleccionada = agregarPrioridadSeleccionado;
	}

	public List<SnapshotCondicion> getSnapshotCondiciones() {
		return snapshotCondiciones;
	}

	public void setSnapshotCondiciones(
			List<SnapshotCondicion> snapshotCondiciones) {
		this.snapshotCondiciones = snapshotCondiciones;
	}

	public SnapshotCondicion getSnapshotCondicionSeleccionado() {
		return snapshotCondicionSeleccionado;
	}

	public void setSnapshotCondicionSeleccionado(
			SnapshotCondicion snapshotCondicionSeleccionado) {
		this.snapshotCondicionSeleccionado = snapshotCondicionSeleccionado;
	}

	public Long getPesoOComparar() {
		return pesoOComparar;
	}

	public void setPesoOComparar(Long pesoOComparar) {
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
		this.cargarNrosDisponibles();
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

	private void cargarNrosDisponibles() {
		this.agregarNro.add(BigDecimal.valueOf(1));
		this.agregarNro.add(BigDecimal.valueOf(10));
		this.agregarNro.add(BigDecimal.valueOf(100));
		this.agregarNro.add(BigDecimal.valueOf(1000));
		this.agregarNro.add(BigDecimal.valueOf(10000));
	}

	private void cargarCriteriosDisponibles() {
		this.agregarCriterio.add(">");
		this.agregarCriterio.add("<");
		this.agregarCriterio.add("Antiguedad");
		this.agregarCriterio.add("Creciente");
		this.agregarCriterio.add("Decreciente");
	}

	private void cargarTiposDeCondiciones() {
		this.tiposCondiciones.add("Taxativa");
		this.tiposCondiciones.add("Cuantitativa");
	}

	@SuppressWarnings("unchecked")
	private void cargarIndicadoresDisponibles() {
		this.agregarIndicador = this.getRepositorindicadores()
				.todosLosNombresDeIndicadores(
						this.getRepositorindicadores().getElementos());
	}

	public void guardarMetodologia() {

		if (this.nombre == null || (this.snapshotCondiciones.isEmpty()))
			throw new RuntimeException(
					"Debe ingresar nombre y al menos una condicion para guardar correctamente. "
							+ "Intentelo nuevamente");
		List<Condicion> condiciones = new ArrayList<Condicion>();

		for (SnapshotCondicion snapshotCondicion : snapshotCondiciones) {
			condiciones.add(new CondicionesBuilder().crear(snapshotCondicion));
		}
 		Metodologia metodologia = new Metodologia(nombre,condiciones);
		getRepositorioMetodologias().agregarMetodologiaNueva(metodologia);
		
	}

	public RepositorioUnicoDeIndicadores getRepositorindicadores() {
		return AplicacionContexto.getInstance().getInstanceRepoIndicadores();
	}
	
	public RepositorioUnicoDeMetodologias getRepositorioMetodologias(){
		return AplicacionContexto.getInstance().getInstanceRepoMetodologias();
	}

	public boolean estanTodosLosDatosTaxativa() {
		boolean creceODecre = true;
		boolean anios = this.getAgregarAniosSeleccionado() != null;
		boolean criterio = this.getAgregarCriterioSeleccionado() != null;
		boolean nro = true;

		if (this.getAgregarCriterioSeleccionado().equals(">")
				|| this.getAgregarCriterioSeleccionado().equals("<")) {
			nro = this.getAgregarNroSeleccionado() != null
					&& this.getAgregarIndicadorSeleccionado() != null;
		}

		if (this.getAgregarCriterioSeleccionado().equals("Creciente")
				|| this.getAgregarCriterioSeleccionado().equals("Decreciente")) {
			creceODecre = this.getAgregarIndicadorSeleccionado() != null;
		}

		return creceODecre && anios && criterio && nro;
	}

	public void agregarCondicion() {		
		this.snapshotCondiciones.add(new SnapshotCondicion(this.getTipoCondicionSeleccionado(),this.getAgregarCriterioSeleccionado(),this.getAgregarIndicadorSeleccionado(),this.getPesoOComparar(),this.getAgregarAniosSeleccionado()));
		this.limpiar();
	}

	public void limpiar() {
		this.setAgregarCriterioSeleccionado(null);
		this.setPesoOComparar(null);
		this.setTipoCondicionSeleccionado(null);
		this.setAgregarNroSeleccionado(null);
		this.setAgregarAniosSeleccionado(null);
		this.setAgregarIndicadorSeleccionado(null);

	}

}
