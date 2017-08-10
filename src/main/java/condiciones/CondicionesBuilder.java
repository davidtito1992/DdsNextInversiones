package condiciones;

import java.math.BigDecimal;

import model.RegistroIndicador;
import model.SnapshotCondicion;
import app.AppData;
import condiciones.CondicionCrecienteODecreciente.Criterio;

public class CondicionesBuilder {

	public Condicion crear(SnapshotCondicion snapshotCondicion) {
		String tipoCondicion = snapshotCondicion.getTipoCondicion();
		String condicion = snapshotCondicion.getNombreCondicion();
		String indicador = snapshotCondicion.getIndicador();
		int ultimosAnios = snapshotCondicion.getAnios();
		BigDecimal pesoOComparar = snapshotCondicion.getPesoOComparar();
		RegistroIndicador registroIndicador = new AppData()
				.getRepositorioIndicadores().getRegistroIndicador(indicador);

		if (tipoCondicion == "Taxativa") {
			switch (condicion) {
			case "Creciente":
				return new CondicionCrecienteODecreciente(Criterio.CRECIENTE,
						registroIndicador, ultimosAnios);
			case "Decreciente":
				return new CondicionCrecienteODecreciente(Criterio.DECRECIENTE,
						registroIndicador, ultimosAnios);
			case "<":
				return new CondicionTaxativaMayorOMenorA(
						condiciones.CondicionSumatoria.Criterio.menorA,
						registroIndicador, ultimosAnios,
						pesoOComparar);
			case ">":
				return new CondicionTaxativaMayorOMenorA(
						condiciones.CondicionSumatoria.Criterio.mayorA,
						registroIndicador, ultimosAnios,
						pesoOComparar);
			case "Antiguedad":
				return new CondicionAntiguedadTaxativa(pesoOComparar);
			default:
				throw new Error("Esto no tendria que pasar");
			}
		} else {
			switch (condicion) {
			case "<":
				return new CondicionCuantitativaMayorOMenorA(
						condiciones.CondicionSumatoria.Criterio.menorA,
						registroIndicador, ultimosAnios, pesoOComparar);
			case ">":
				return new CondicionCuantitativaMayorOMenorA(
						condiciones.CondicionSumatoria.Criterio.mayorA,
						registroIndicador, ultimosAnios, pesoOComparar);
			case "Antiguedad":
				return new CondicionAntiguedadCuantitativa(pesoOComparar);
			default:
				throw new Error("Esto no tendria que pasar");
			}
		}
	}
}
