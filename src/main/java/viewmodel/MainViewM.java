package viewmodel;

import org.uqbar.commons.utils.Observable;

import repositories.RepositorioEmpresa;

@Observable
public class MainViewM {

	RepositorioEmpresa repoEmpresa;

	private boolean empresasSinCargar = true;
	private boolean indicadoresSinCargar = true;
	
	public MainViewM() {

	}

	public boolean isEmpresasSinCargar() {
		return empresasSinCargar;
	}

	public void setEmpresasSinCargar(boolean empresasSinCargar) {
		this.empresasSinCargar = empresasSinCargar;
	}

	public boolean isIndicadoresSinCargar() {
		return indicadoresSinCargar;
	}

	public void setIndicadoresSinCargar(boolean indicadoresSinCargar) {
		this.indicadoresSinCargar = indicadoresSinCargar;
	}

	// COMBOBOX CONSULTAS A REALIZAR

	// private Collection<String> tipoConsultas = new ArrayList<String>();
	// private String consultaSeleccionada ;
	//
	// public Collection<String> getTipoConsultas() { return tipoConsultas; }
	//
	// public void setTipoConsultas(Collection<String> tipoConsultas) {
	// this.tipoConsultas = tipoConsultas; }
	//
	// public String getConsultaSeleccionada() { return consultaSeleccionada; }
	//
	// public void setConsultaSeleccionada(String consultaSeleccionada) {
	// this.consultaSeleccionada = consultaSeleccionada; }
	//
	// public MainViewM(){ agregarConsultas(); }
	//
	// public void agregarConsultas(){ tipoConsultas.add("Indicadores");
	// tipoConsultas.add("Metodologias"); tipoConsultas.add("Graficos"); }
}