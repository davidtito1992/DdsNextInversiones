package condiciones;

import java.math.BigDecimal;

import model.RegistroIndicador;
import model.SnapshotCondicion;
import app.AppData;
import condiciones.CondicionCrecienteODecreciente.CreceODecrece;

public class CondicionesBuilder {

	public static final String TAXATIVA = "Taxativa";
	public static final String CRECIENTE = "Creciente";
	public static final String DECRECIENTE = "Decreciente";
	public static final String ANTIGUEDAD = "Antiguedad";
	public static final String MAYOR = ">";
	public static final String MENOR = "<";

	public Condicion crear(SnapshotCondicion snapshotCondicion) {

		String tipoCondicion = snapshotCondicion.getTipoCondicion();
		String condicion = snapshotCondicion.getNombreCondicion();
		String indicador = snapshotCondicion.getIndicador();
		int ultimosAnios = snapshotCondicion.getAnios();
		BigDecimal pesoOComparar = snapshotCondicion.getPesoOComparar();
		RegistroIndicador registroIndicador = new AppData()
				.getRepositorioIndicadores().getRegistroIndicador(indicador);

		if (tipoCondicion.equals(TAXATIVA)) {
			switch (condicion) {
			case CRECIENTE:
				return new CondicionCrecienteODecreciente(CreceODecrece.CRECIENTE,
						registroIndicador, ultimosAnios);
			case DECRECIENTE:
				return new CondicionCrecienteODecreciente(CreceODecrece.DECRECIENTE,
						registroIndicador, ultimosAnios);
			case MENOR:
				return new CondicionTaxativaMayorOMenorA(
						condiciones.CondicionSumatoria.Criterio.menorA,
						registroIndicador, ultimosAnios, pesoOComparar);
			case MAYOR:
				return new CondicionTaxativaMayorOMenorA(
						condiciones.CondicionSumatoria.Criterio.mayorA,
						registroIndicador, ultimosAnios, pesoOComparar);
			case ANTIGUEDAD:
				return new CondicionTaxativaAntiguedad(pesoOComparar);
			default:
				throw new RuntimeException(
						"El tipo de condicion elegida no esta disponible");
			}
		} else {
			switch (condicion) {
			case MENOR:
				return new CondicionCuantitativaMayorOMenorA(
						condiciones.CondicionSumatoria.Criterio.menorA,
						registroIndicador, ultimosAnios, pesoOComparar);
			case MAYOR:
				return new CondicionCuantitativaMayorOMenorA(
						condiciones.CondicionSumatoria.Criterio.mayorA,
						registroIndicador, ultimosAnios, pesoOComparar);
			case ANTIGUEDAD:
				return new CondicionCuantitativaAntiguedad(pesoOComparar);
			default:
				throw new RuntimeException(
						"El tipo de condicion elegida no esta disponible");
			}
		}
	}
}
