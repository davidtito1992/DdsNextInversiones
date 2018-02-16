package main.viewmodel;

import main.app.AppData;
import model.RegistroIndicador;

import org.uqbar.commons.utils.Observable;

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
