package app;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;

import model.RepositorioMaestro;
import model.SnapshotEmpresa;

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
	
	public Collection<SnapshotEmpresa> llenarTabla(){
		Collection<SnapshotEmpresa> listSnap = new ArrayList<SnapshotEmpresa>() ;
		RepositorioMaestro.empresas.forEach(e->{
			e.getPeriodos().forEach(p->{
				p.getCuentas().forEach(c->{
					SnapshotEmpresa sse = new SnapshotEmpresa();
					sse.setCuenta(c.getNombre());
					sse.setValor(c.getValor());
					sse.setNombre(e.getNombre());
					sse.setSemestre(p.getSemestre());
					sse.setAño(p.getAño());
					listSnap.add(sse);
				});
			});
		});
		return listSnap;
	}
}
