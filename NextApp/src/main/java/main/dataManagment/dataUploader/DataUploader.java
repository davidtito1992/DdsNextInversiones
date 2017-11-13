package main.dataManagment.dataUploader;

import model.Metodologia;
import model.RegistroIndicador;

public interface DataUploader {

	public void escribirNuevoIndicador(RegistroIndicador unRegIndicador)
			throws Exception;

	public void borrarIndicador(RegistroIndicador unRegIndicador)
			throws Exception;

	public void escribirNuevaMetodologia(Metodologia unaMetodologia);

	public void borrarMetodologia(Metodologia unaMetodologia);

}
