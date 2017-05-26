package DataManagment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.bindings.keys.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Empresa;
import model.Indicador;

public class JsonAdapter implements DataAdapter {

	public List<Empresa> adaptarEmpresas(String empresas) throws Exception {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		try {
			Type listType = new TypeToken<List<Empresa>>() {
			}.getType();
			listaEmpresas = new Gson().fromJson(empresas, listType);

		} catch (Exception e) {
			throw new ParseException(
					"El archivo no pudo ser leido correctamente, verifique la sintaxis y vuelva a intentarlo.");
		}
		return listaEmpresas;
	}

	public List<Indicador> adaptarIndicadores(String indicadores)
			throws Exception {
		List<Indicador> listaIndicadores = new ArrayList<Indicador>();
		try {
			Type listType = new TypeToken<List<Indicador>>() {
			}.getType();
			listaIndicadores = new Gson().fromJson(indicadores, listType);

		} catch (Exception e) {
			throw new ParseException(
					"El archivo no pudo ser leido correctamente, verifique la sintaxis y vuelva a intentarlo.");
		}
		return listaIndicadores;
	}
}
