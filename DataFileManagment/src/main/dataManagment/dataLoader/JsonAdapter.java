package main.dataManagment.dataLoader;

import java.lang.reflect.Type;
import java.util.ArrayList;

import model.Empresa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.eclipse.jface.bindings.keys.ParseException;

public class JsonAdapter implements DataAdapter {

	Gson gson = GsonFactory.getGson();

	public ArrayList<Empresa> adaptarEmpresas(String empresas)
			throws ParseException {
		ArrayList<Empresa> listaEmpresas = new ArrayList<Empresa>();
		try {
			Type listType = new TypeToken<ArrayList<Empresa>>() {
			}.getType();
			listaEmpresas = gson.fromJson(empresas, listType);

		} catch (Exception e) {
			throw new ParseException(
					"El archivo no pudo ser leido correctamente, verifique la sintaxis y vuelva a intentarlo.");
		}
		return listaEmpresas;
	}

}
