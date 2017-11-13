package main.dataManagment.dataLoader;

import java.util.ArrayList;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

public interface DataLoader {

	public ArrayList<Empresa> getDataEmpresas() throws Exception;

	public ArrayList<RegistroIndicador> getDataIndicadores() throws Exception;
	
	public ArrayList<Metodologia> getDataMetodologias() throws Exception;

}
