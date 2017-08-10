package app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import condiciones.Condicion;
import condiciones.CondicionCrecienteODecreciente;
import condiciones.CondicionCuantitativaAntiguedad;
import condiciones.CondicionCuantitativaMayorOMenorA;
import condiciones.CondicionTaxativaAntiguedad;
import condiciones.CondicionSumatoria.Criterio;
import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;
import repositories.RepositorioUnicoDeEmpresas;
import repositories.RepositorioUnicoDeIndicadores;
import repositories.RepositorioUnicoDeMetodologias;
import dataManagment.dataLoader.DataLoader;
import dataManagment.dataLoader.DataLoaderFactory;
import dataManagment.dataUploader.DataUploader;
import dataManagment.dataUploader.DataUploaderFactory;

public class AppData {

	@SuppressWarnings("unchecked")
	public void cargarEmpresas() throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		ArrayList<Empresa> empresas = cargador.getDataEmpresas();
		this.getRepositorioEmpresas().cargarListaDeElementos(empresas);
	}

	@SuppressWarnings("unchecked")
	public void cargarMetodologias() throws Exception {
		List<Condicion> condicionesWarren = new ArrayList<Condicion>();
		RegistroIndicador ROE;
		RegistroIndicador propDeu;
		RegistroIndicador i4;

		i4 = new AppData().getRepositorioIndicadores().getRegistroIndicador(
				"i4");
		ROE = new AppData().getRepositorioIndicadores().getRegistroIndicador(
				"ROE");
		propDeu = new AppData().getRepositorioIndicadores()
				.getRegistroIndicador("ProporcionDeDeuda");

		condicionesWarren.add(new CondicionCuantitativaMayorOMenorA(
				Criterio.mayorA, ROE, 10, new BigDecimal(5)));// roe creciente
		condicionesWarren.add(new CondicionCuantitativaMayorOMenorA(
				Criterio.menorA, propDeu, 10, new BigDecimal(3)));// proporcion
																	// de deuda
																	// mas chico
		condicionesWarren.add(new CondicionCrecienteODecreciente(
				CondicionCrecienteODecreciente.Criterio.CRECIENTE, i4, 10));// Margenes
																			// crecientes
		condicionesWarren.add(new CondicionTaxativaAntiguedad(
				new BigDecimal(10)));// mayor a 10 años
		condicionesWarren.add(new CondicionCuantitativaAntiguedad(
				new BigDecimal(2)));// las mas importantes son las mas antiguas

		Metodologia metodologia = new Metodologia("WarrenBuffet",
				condicionesWarren);
		ArrayList<Metodologia> metodologias = new ArrayList<Metodologia>();
		metodologias.add(metodologia);
		this.getRepositorioMetodologias().cargarListaDeElementos(metodologias);
	}

	@SuppressWarnings("unchecked")
	public void cargarIndicadores() throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		ArrayList<RegistroIndicador> indicadores = cargador
				.getDataIndicadores();
		this.getRepositorioIndicadores().cargarListaDeElementos(indicadores);
	}

	public void borrarMetodologia(Metodologia metSelec) {
		this.getRepositorioMetodologias().borrarElemento(metSelec);
	}

	public RepositorioUnicoDeIndicadores getRepositorioIndicadores() {
		return AplicacionContexto.getInstance().getInstanceRepoIndicadores();
	}

	public RepositorioUnicoDeEmpresas getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}

	public RepositorioUnicoDeMetodologias getRepositorioMetodologias() {
		return AplicacionContexto.getInstance().getInstanceRepoMetodologias();
	}

	@SuppressWarnings("unchecked")
	public void guardarIndicador(RegistroIndicador unIndicador) {

		try {
			DataUploader cargador = DataUploaderFactory
					.actualizarData(DataLoaderFactory.ARCHIVO);

			cargador.escribirNuevoIndicador(unIndicador);
			this.getRepositorioIndicadores().add(unIndicador);

		} catch (Exception e) {
			throw new RuntimeException(
					"Debido a un problema en la lectura y/o escritura del archivo no pudimos realizar la operacion :/");
		}
	}

	@SuppressWarnings("unchecked")
	public void borrarIndicador(RegistroIndicador unIndicador) {

		try {
			DataUploader cargador = DataUploaderFactory
					.actualizarData(DataLoaderFactory.ARCHIVO);

			cargador.borrarIndicador(unIndicador);
			this.getRepositorioIndicadores().delete(unIndicador);

		} catch (Exception e) {
			throw new RuntimeException(
					"Debido a un problema en la lectura y/o escritura del archivo no pudimos realizar la operacion :/");
		}
	}

	public void guardarMetodologia(Metodologia metodologia) {

		try {
			// String nuevaMetodologiaString = new AdapterToJson()
			// .getStringMetodologia(metodologia);
			//
			// // sobreescribimos un archivo segun nombre de archivo,
			// textoviejo,textonuevo
			// new FileUploader("./metodologias.json", "}]",
			// nuevaMetodologiaString
			// + "]\r\n");
			//
			// this.getRepoMetodologias().create(metodologia);

			DataUploader cargador = DataUploaderFactory
					.actualizarData(DataLoaderFactory.ARCHIVO);

			cargador.escribirNuevaMetodologia(metodologia);
			this.getRepositorioMetodologias().agregarMetodologiaNueva(
					metodologia);

		} catch (Exception e) {
			throw new RuntimeException(
					"Debido a un problema en la lectura y/o escritura del archivo no pudimos realizar la operacion :/");
		}

	}

}
