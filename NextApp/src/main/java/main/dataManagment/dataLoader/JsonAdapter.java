package main.dataManagment.dataLoader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;
import model.SnapshotIndicador;

import org.eclipse.jface.bindings.keys.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonAdapter implements DataAdapter {

	Gson gson = GsonFactory.getGson();

	public ArrayList<Empresa> adaptarEmpresas(String empresas) throws ParseException {
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

	public ArrayList<RegistroIndicador> adaptarIndicadores(String indicadores) throws ParseException {
		ArrayList<RegistroIndicador> listaIndicadores = new ArrayList<RegistroIndicador>();
		try {
			Type listType = new TypeToken<ArrayList<RegistroIndicador>>() {
			}.getType();
			listaIndicadores = gson.fromJson(indicadores, listType);

		} catch (Exception e) {
			throw new ParseException(
					"El archivo no pudo ser leido correctamente, verifique la sintaxis y vuelva a intentarlo.");
		}
		return listaIndicadores;
	}

	public List<SnapshotIndicador> adaptarSnapshotIndicadores(String snapshots) {
		ArrayList<SnapshotIndicador> listaIndicadores = new ArrayList<SnapshotIndicador>();

		Type listType = new TypeToken<ArrayList<SnapshotIndicador>>() {
		}.getType();
		listaIndicadores = gson.fromJson(snapshots, listType);
		return listaIndicadores;
	}

	public ArrayList<Metodologia> adaptarMetodologias(String metodologias) throws ParseException {
		ArrayList<Metodologia> listaMetodologias = new ArrayList<Metodologia>();
		try {
			Type listType = new TypeToken<ArrayList<Metodologia>>() {
			}.getType();
			listaMetodologias = gson.fromJson(metodologias, listType);

		} catch (Exception e) {
			throw new ParseException(
					"El archivo no pudo ser leido correctamente, verifique la sintaxis y vuelva a intentarlo.");
		}
		return listaMetodologias;
	}
}
