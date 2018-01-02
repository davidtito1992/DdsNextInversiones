package main.dataManagment.dataLoader;

import java.lang.reflect.Type;
import java.util.ArrayList;

import model.EmpresaModificacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.eclipse.jface.bindings.keys.ParseException;

public class JsonAdapter implements DataAdapter {

	Gson gson = GsonFactory.getGson();

	public ArrayList<EmpresaModificacion> adaptarEmpresas(String empresas)
			throws ParseException {
		ArrayList<EmpresaModificacion> listaEmpresas = new ArrayList<EmpresaModificacion>();
		try {
			Type listType = new TypeToken<ArrayList<EmpresaModificacion>>() {
			}.getType();
			listaEmpresas = gson.fromJson(empresas, listType);

		} catch (Exception e) {
			throw new ParseException(
					"El archivo no pudo ser leido correctamente, verifique la sintaxis y vuelva a intentarlo.");
		}
		return listaEmpresas;
	}

}
