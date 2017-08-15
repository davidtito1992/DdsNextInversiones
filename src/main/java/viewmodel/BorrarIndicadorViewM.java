package viewmodel;

import model.RegistroIndicador;

import org.uqbar.commons.utils.Observable;

import app.AppData;

@Observable
public class BorrarIndicadorViewM {

	private RegistroIndicador indicadorABorrar;

	public BorrarIndicadorViewM(RegistroIndicador indicadorABorrar) {

		this.indicadorABorrar = indicadorABorrar;
	}

	public void borrar() throws Exception {

		new AppData().borrarIndicador(indicadorABorrar);

	}

}
