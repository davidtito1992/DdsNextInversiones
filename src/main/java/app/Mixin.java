package app;

import java.io.File;
import java.io.FileReader;

import model.Empresa;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.uqbar.commons.utils.ApplicationContext;

import repositories.RepositorioEmpresa;

public class Mixin {

	public Mixin (){
		//a modo de prueba del repo de arena
		cargarEmpresas();
	}
	
	public  void cargarEmpresas() {
		JSONParser parser = new JSONParser();
		String AbsolutePath = new File(".").getAbsolutePath();
		try {

			Object obj = parser.parse(new FileReader(AbsolutePath
					+ "/empresas.json"));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray arrayDeEmpresas = (JSONArray) jsonObject.get("empresas");

			for (int i = 0; i < arrayDeEmpresas.size(); i++) {
				
				getRepoEmpresas().create((JsonAdapter.adaptarEmpresa((JSONObject) arrayDeEmpresas.get(i))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RepositorioEmpresa getRepoEmpresas() {
		return (RepositorioEmpresa) ApplicationContext.getInstance().getSingleton(Empresa.class);
	}
	
}
