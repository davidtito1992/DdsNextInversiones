package main.dataManagment.dataLoader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import main.app.AppData;
import main.condiciones.Condicion;
import main.condiciones.CondicionCrecienteODecreciente;
import main.condiciones.CondicionCrecienteODecreciente.CreceODecrece;
import main.condiciones.CondicionCuantitativaAntiguedad;
import main.condiciones.CondicionCuantitativaMayorOMenorA;
import main.condiciones.CondicionSumatoria.MenorOMayor;
import main.condiciones.CondicionTaxativaAntiguedad;
import model.Metodologia;
import model.RegistroIndicador;

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
				MenorOMayor.mayorA, ROE, 10, new BigDecimal(5)));// roe creciente
		condicionesWarren.add(new CondicionCuantitativaMayorOMenorA(
				MenorOMayor.menorA, propDeu, 10, new BigDecimal(3)));// proporcion
																	// de deuda
																	// mas chico
		condicionesWarren.add(new CondicionCrecienteODecreciente(
				CreceODecrece.CRECIENTE, i4, 10));// Margenes
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
