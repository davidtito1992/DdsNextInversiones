package indicadoresCondicionados;

import java.util.ArrayList;
import java.util.List;

public class IndicadorCondicionado {
	private byte prioridad;
	private String nombreIndicador;
	private List<Condicion> condiciones;

	public IndicadorCondicionado(byte prioridad, String nombre,
			List<Condicion> condiciones) {

		this.prioridad = prioridad;
		this.nombreIndicador = nombre;
		this.condiciones = condiciones;
	}

	public String getNombreIndicador() {
		return nombreIndicador;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setProridad(byte proridad) {
		this.prioridad = proridad;
	}

	public ArrayList<RankingEmpresa> calcular(
			ArrayList<RankingEmpresa> listEmpresas) {

		return listEmpresas;
	}

}
