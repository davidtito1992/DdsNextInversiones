package main.viewmodel;

import main.app.AppData;
import model.Metodologia;

import org.uqbar.commons.utils.Observable;

@Observable
public class BorrarMetodologiaViewM {

	private Metodologia metodologiaABorrar;

	public BorrarMetodologiaViewM(Metodologia metodologiaABorrar) {

		this.metodologiaABorrar = metodologiaABorrar;
	}

	public void borrar() throws Exception {
		new AppData().borrarMetodologia(this.metodologiaABorrar);

	}
	
}
