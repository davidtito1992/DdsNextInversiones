package app;
import java.util.List;

import org.uqbar.commons.utils.ApplicationContext;

import formulaIndicador.FormulaIndicador;
import parserIndicador.ParseException;
import parserIndicador.ParserIndicador;
import model.Empresa;
import model.RegistroIndicador;
import DataManagment.AdapterToJson;
import DataManagment.DataLoader;
import DataManagment.DataLoaderFactory;
import DataManagment.FileWriter;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import semanticaIndicador.AnalizadorSemantico;

public class AppData {

	public void cargarEmpresas() throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		List<Empresa> empresas = cargador.getDataEmpresas();

		// CARGO EN REPO
		this.getRepoEmpresas().cargarListaEmpresas(empresas);

	}

	public void cargarIndicadores() throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		List<RegistroIndicador> indicadores = cargador.getDataIndicadores();

		// CARGO EN REPO
		this.getRepoIndicadores().cargarListaIndicadores(indicadores);

	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(RegistroIndicador.class);
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}

	public void guardarIndicador(RegistroIndicador unIndicador) throws Throwable {

	
		ParserIndicador preIndicador = new ParserIndicador(unIndicador.getFormula());
		preIndicador.pasear() ;
		new AnalizadorSemantico(preIndicador.variables()).analizar();
		
		try {
			unIndicador.setVariables(preIndicador.variables());
			String nuevoIndicadorString = new AdapterToJson(unIndicador)
					.getstringJson();

			// sobreescribimos un archivo segun nombre de archivo,
			// textoviejo,textonuevo
			new FileWriter("./indicadores.json", "}]", nuevoIndicadorString
					+ "]\r\n");

			this.getRepoIndicadores().create(unIndicador);

		} catch (Exception e) {
			throw new Exception(
					"Debido a un problema en la lectura y/o escritura del archivo no pudimos realizar la operacion :/");
		}

	}

	public void borrarIndicador(RegistroIndicador unIndicador) throws Exception {

		try {
			// Convertimos un indicador a json
			String nuevoIndicadorString = new AdapterToJson(new RegistroIndicador(
					unIndicador.getNombre(), unIndicador.getFormula()))
					.getstringJson();
			// sobreescribimos para borrar
			new FileWriter("./indicadores.json", nuevoIndicadorString, "");

			// eliminamos del repo
			this.getRepoIndicadores().delete(unIndicador);

		} catch (Exception e) {
			throw new Exception(
					"Debido a un problema en la lectura y/o escritura del archivo no pudimos realizar la operacion :/");
		}
	}

}
