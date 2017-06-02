package viewmodel;

import org.uqbar.commons.utils.Observable;

import app.AppData;

@Observable
public class MainViewM {

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
	
	public void cargarIndicadores() throws Exception{
		new AppData().cargarIndicadores();
		System.out.println("Accediendo para ver las indicadores...");
	}

	public void cargarEmpresas() throws Exception {
		new AppData().cargarEmpresas();
		System.out.println("Accediendo para ver las empresas...");
	}
	
	
}