package dataManagment.dataLoader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.Metodologia;
import model.RegistroIndicador;
import app.AppData;
import condiciones.Condicion;
import condiciones.CondicionCrecienteODecreciente;
import condiciones.CondicionCuantitativaAntiguedad;
import condiciones.CondicionCuantitativaMayorOMenorA;
import condiciones.CondicionTaxativaAntiguedad;
import condiciones.CondicionSumatoria.Criterio;

public class MetodologiasLoader {

	public static RegistroIndicador ROE;
	public static RegistroIndicador propDeu;
	public static RegistroIndicador i4;

	public static List<Metodologia> damePredefinidas() {

		inicializarVariables();

		List<Condicion> condicionesWarren = new ArrayList<Condicion>();
		agregarCondicionesA(condicionesWarren);
		Metodologia metodologia = new Metodologia("WarrenBuffet",
				condicionesWarren);

		List<Metodologia> metodologias = new ArrayList<Metodologia>();
		metodologias.add(metodologia);

		return metodologias;
	}

	private static void agregarCondicionesA(List<Condicion> condicionesWarren) {
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

	}

	private static void inicializarVariables() {
		i4 = new AppData().getRepositorioIndicadores().getRegistroIndicador(
				"i4");
		ROE = new AppData().getRepositorioIndicadores().getRegistroIndicador(
				"ROE");
		propDeu = new AppData().getRepositorioIndicadores()
				.getRegistroIndicador("ProporcionDeDeuda");
	}

}
