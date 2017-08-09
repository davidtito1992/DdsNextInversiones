package viewmodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.RegistroIndicador;

import org.uqbar.commons.utils.Observable;

import condiciones.Condicion;
import repositories.RepositorioUnicoDeIndicadores;
import app.AplicacionContexto;
import ex_condiciones.CondicionCualitativa;
import ex_condiciones.CondicionTaxativa;
import ex_condiciones.CondicionTaxativaAntiguedad;
import ex_condiciones.CondicionTaxativaCreciente;
import ex_condiciones.CondicionTaxativaDecreciente;
import ex_condiciones.CondicionTaxativaMayorA;
import ex_condiciones.CondicionTaxativaMenorA;

@Observable
public class AgregarMetodologiaViewM {

	/********* ATRIBUTOS *********/

	private List<CondicionTaxativa> listaTaxativas = new ArrayList<CondicionTaxativa>();
	private List<CondicionCualitativa> listaCualitativas = new ArrayList<CondicionCualitativa>();	
	private CondicionCualitativa cualitativa;
	private CondicionTaxativa taxativa;
	
	private List<Condicion> listaDeIndicadoresCondicionados = new ArrayList<Condicion>();

	private String nombre;
	private List<Integer> prioridades =  new ArrayList<Integer>();
	private Integer agregarPrioridadSeleccionada;
	private List<String> agregarIndicador;
	private String agregarIndicadorSeleccionado;
	private List<String> agregarCriterio = new ArrayList<String>();
	private String agregarCriterioSeleccionado;
	private List<BigDecimal> agregarNro = new ArrayList<BigDecimal>();
	private BigDecimal agregarNroSeleccionado;
	private List<Integer> agregarAnios = new ArrayList<Integer>();
	private Integer agregarAniosSeleccionado;

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

	public List<CondicionTaxativa> getListaTaxativas() {
		return listaTaxativas;
	}

	public void setListaTaxativas(List<CondicionTaxativa> listaTaxativas) {
		this.listaTaxativas = listaTaxativas;
	}

	public List<CondicionCualitativa> getListaCualitativas() {
		return listaCualitativas;
	}

	public void setListaCualitativas(
			List<CondicionCualitativa> listaCualitativas) {
		this.listaCualitativas = listaCualitativas;
	}

	public CondicionCualitativa getCualitativa() {
		return cualitativa;
	}

	public void setCualitativa(CondicionCualitativa cualitativa) {
		this.cualitativa = cualitativa;
	}

	public CondicionTaxativa getTaxativa() {
		return taxativa;
	}

	public void setTaxativa(CondicionTaxativa taxativa) {
		this.taxativa = taxativa;
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

	public void setAgregarPrioridadSeleccionada(Integer agregarPrioridadSeleccionado) {
		this.agregarPrioridadSeleccionada = agregarPrioridadSeleccionado;
	}
	public List<Integer> getPrioridades() {
		return prioridades;
	}

	public void setPrioridades(List<Integer> prioridades) {
		this.prioridades = prioridades;
	}
	public List<Condicion> getListaDeIndicadoresCondicionados() {
		return listaDeIndicadoresCondicionados;
	}

	public void setListaDeIndicadoresCondicionados(
			List<Condicion> listaDeIndicadoresCondicionados) {
		this.listaDeIndicadoresCondicionados = listaDeIndicadoresCondicionados;
	}
	/********* METODOS *********/

	public AgregarMetodologiaViewM() {
		this.cargarIndicadoresDisponibles();
		this.cargarCriteriosDisponibles();
		this.cargarAniosDisponibles();
		this.cargarNrosDisponibles();
		this.cargarPrioridades();
	}
	private void cargarPrioridades() {
		
		this.prioridades.add(1);
		this.prioridades.add(2);
		this.prioridades.add(3);
		this.prioridades.add(4);
		this.prioridades.add(5);
		this.prioridades.add(6);
		this.prioridades.add(7);
		this.prioridades.add(8);
		
	}
	private void cargarAniosDisponibles() {
		this.agregarAnios.add(1);
		this.agregarAnios.add(2);
		this.agregarAnios.add(3);
		this.agregarAnios.add(4);
		this.agregarAnios.add(5);
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

	@SuppressWarnings("unchecked")
	private void cargarIndicadoresDisponibles() {
		this.agregarIndicador = this.getRepositorindicadores()
				.todosLosNombresDeIndicadores(
						this.getRepositorindicadores().getElementos());
	}

	public void guardarMetodologia() {

//		if (this.nombre == null
//				|| (this.listaCualitativas.isEmpty() && this.listaTaxativas
//						.isEmpty())) {
//			throw new RuntimeException(
//					"Debe ingresar nombre y al menos una condicion para guardar correctamente. "
//							+ "Intentelo nuevamente");
//		}

		if (this.nombre == null
				|| (this.listaCualitativas.isEmpty()))
			throw new RuntimeException(
					"Debe ingresar nombre y al menos una condicion para guardar correctamente. "
							+ "Intentelo nuevamente");
		
//		Metodologia laNueva = new Metodologia(this.getNombre(),
//				this.getListaTaxativas(), this.getListaCualitativas());
	//	Metodologia nuevaMetodologia = new Metodologia(this.getNombre(),this.getListaDeIndicadoresCondicionados()) ;

		//new AppData().guardarMetodologia(nuevaMetodologia);
	}

//	public RepositorioIndicadores getRepoIndicadores() {
//		return ApplicationContext.getInstance().getSingleton(
//				RegistroIndicador.class);
//	}
	
	public RepositorioUnicoDeIndicadores getRepositorindicadores(){
		return AplicacionContexto.getInstance().getInstanceRepoIndicadores();
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

	public void agregarCondicionTaxativa() {

		if (this.estanTodosLosDatosTaxativa()) {
			CondicionTaxativa taxativa = crearCondicion();
			this.setTaxativa(taxativa);
			this.listaTaxativas.add(taxativa);
		}
	}

	private CondicionTaxativa crearCondicion() {
		switch (this.getAgregarCriterioSeleccionado()) {

		case "<":
			return newTaxativaMenorA();
		case ">":
			return newTaxativaMayorA();
		case "Antiguedad":
			return newTaxativaAntiguedad();
		case "Creciente":
			return newTaxativaCreciente();
		case "Decreciente":
			return newTaxativaDecreciente();
		default:
			throw new RuntimeException(
					"Debe elegir un criterio para esta condicion");
		}
	}

	private CondicionTaxativa newTaxativaMayorA() {

		RegistroIndicador indicador = getRepositorindicadores()
				.getRegistroIndicador(this.getAgregarIndicadorSeleccionado());
		return new CondicionTaxativaMayorA(indicador,
				this.getAgregarNroSeleccionado(),
				this.getAgregarAniosSeleccionado());
	}

	private CondicionTaxativa newTaxativaAntiguedad() {
		return new CondicionTaxativaAntiguedad(
				this.getAgregarAniosSeleccionado());
	}

	private CondicionTaxativa newTaxativaCreciente() {
		RegistroIndicador indicador = getRepositorindicadores()
				.getRegistroIndicador(this.getAgregarIndicadorSeleccionado());
		return new CondicionTaxativaCreciente(indicador,
				this.getAgregarAniosSeleccionado());
	}

	private CondicionTaxativa newTaxativaDecreciente() {
		RegistroIndicador indicador = getRepositorindicadores()
				.getRegistroIndicador(this.getAgregarIndicadorSeleccionado());
		return new CondicionTaxativaDecreciente(indicador,
				this.getAgregarAniosSeleccionado());
	}

	private CondicionTaxativa newTaxativaMenorA() {
		RegistroIndicador indicador = getRepositorindicadores()
				.getRegistroIndicador(this.getAgregarIndicadorSeleccionado());
		return new CondicionTaxativaMenorA(indicador,
				this.getAgregarNroSeleccionado(),
				this.getAgregarAniosSeleccionado());
	}

}
