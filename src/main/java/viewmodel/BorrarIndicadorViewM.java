package viewmodel;

import model.Indicador;

import org.uqbar.commons.utils.Observable;

import app.AppData;

@Observable
public class BorrarIndicadorViewM {

	private Indicador indicadorABorrar;

	public BorrarIndicadorViewM( Indicador indicadorABorrar){
		
	this.indicadorABorrar =  indicadorABorrar;	
	}
	

	public void borrar() throws Exception{
		new AppData().borrarIndicador(this.indicadorABorrar);
	
	}

}
