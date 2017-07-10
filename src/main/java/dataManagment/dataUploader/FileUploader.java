package dataManagment.dataUploader;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import model.Metodologia;
import model.RegistroIndicador;

public class FileUploader implements DataUploader {

	private void agregarTexto(String nombreRuta, String textoNuevo)
			throws Exception {
		BufferedReader file = new BufferedReader(new FileReader(nombreRuta));
		String line;
		String input = "";

		while ((line = file.readLine()) != null) {

			/* ENCONTRAMOS LA ETIQUETA DE FINALIZACION Y REEMPLAZAMOS STRING */
			if (line.contains("}]")) {
				input += line.replaceAll("}]", "},\n" + textoNuevo);
			} else if (line.contains("[]")) {
				input += line.replaceAll("]", textoNuevo);
			} else
				input += line + "\r\n";
		}
		this.escribir(nombreRuta, input);

	}

	private boolean eliminarTexto(String nombreRuta, String textoViejo)
			throws Exception {

		boolean finalArchivo = false;
		BufferedReader file = new BufferedReader(new FileReader(nombreRuta));
		String line;
		String input = "";
		boolean escribio = false;
		while ((line = file.readLine()) != null) {
			if (line.contains("[" + textoViejo + "]")) {
				input += line.replace(textoViejo, "");
				escribio = true;
			} else if (line.contains(textoViejo + ",")) {
				input += line.replace(textoViejo + ",", "");
				escribio = true;
			} else if (line.contains(textoViejo + "]")) {
				finalArchivo = true;
				input += line.replace(line.toString(), "");
				escribio = true;

			} else {
				input += line + "\r\n";
			}
		}
		this.escribir(nombreRuta, input);
		if (escribio == false) {
			throw new RuntimeException();
		}
		return finalArchivo;
	}

	private void escribir(String nombreRuta, String input) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(nombreRuta);
		fileOut.write(input.getBytes());
		fileOut.close();
	}

	@Override
	public void escribirNuevoIndicador(RegistroIndicador unRegIndicador)
			throws Exception {

		AdapterToData adaptador = AdapterToDataFactory
				.adaptarAData(AdapterToDataFactory.JSON);

		String nuevoIndicadorString = adaptador
				.getStringRegistroIndicador(unRegIndicador);

		this.agregarTexto("./indicadores.json", nuevoIndicadorString + "]\r\n");

	}

	@Override
	public void borrarIndicador(RegistroIndicador unRegIndicador)
			throws Exception {

		RegistroIndicador regIndicador = new RegistroIndicador(
				unRegIndicador.getNombre(), unRegIndicador.getFormula(),
				unRegIndicador.getVariables());

		AdapterToData adaptador = AdapterToDataFactory
				.adaptarAData(AdapterToDataFactory.JSON);

		String nuevoIndicadorString = adaptador
				.getStringRegistroIndicador(regIndicador);

		boolean finalArchivo = this.eliminarTexto("./indicadores.json",
				nuevoIndicadorString);

		if (finalArchivo) {
			BufferedReader file = new BufferedReader(new FileReader(
					"./indicadores.json"));
			String line;
			String input = "";
			int ultimaLinea = (int) file.lines().count();
			int cont = 0;
			file = new BufferedReader(new FileReader("./indicadores.json"));
			while ((line = file.readLine()) != null) {
				cont++;
				if (cont == ultimaLinea) {
					input += line.replaceAll("},", "}]");
				} else
					input += line + "\r\n";

			}
			this.escribir("./indicadores.json", input);
		}
	}

	@Override
	public void escribirNuevaMetodologia(Metodologia unaMetodologia) {

	}

	@Override
	public void borrarMetodologia(Metodologia unaMetodologia) {

	}

}