package app;

import java.io.File;
import java.io.FileReader;
import model.RepositorioMaestro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Mixin {

	public static void cargarEmpresas(){
		JSONParser parser = new JSONParser();
		String AbsolutePath = new File(".").getAbsolutePath();
        try {
 
            Object obj = parser.parse(new FileReader(
                    AbsolutePath+"/empresas.json"));
 
            JSONObject jsonObject = (JSONObject) obj;
            
            JSONArray arrayDeEmpresas = (JSONArray) jsonObject.get("empresas");
            
            for (int i = 0; i < arrayDeEmpresas.size(); i++) {
				RepositorioMaestro.empresas.add(JsonAdapter.adaptarEmpresa((JSONObject) arrayDeEmpresas.get(i)));
			}    
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
	
	
	
}
