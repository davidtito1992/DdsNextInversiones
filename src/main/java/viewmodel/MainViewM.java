package viewmodel;

import org.uqbar.commons.utils.Observable;

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
}