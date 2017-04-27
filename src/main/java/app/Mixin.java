package app;

import java.io.File;
import java.io.FileReader;

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
            
            System.out.println(jsonObject.get("empresas"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}
