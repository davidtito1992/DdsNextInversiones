package indicadoresCondicionados;

import java.util.ArrayList;
import java.util.List;

public class IndicadorCondicionado {
	private byte  proridad;
	private String nombreIndicador;
	private List<Condicion> condiciones;

	public IndicadorCondicionado(byte prioridad, String nombre, List<Condicion> condiciones) {
	
		this.proridad = prioridad ;
		this.nombreIndicador = nombre;
		this.condiciones = condiciones;
	}

	public String getNombreIndicador() {
		return nombreIndicador;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public int getProridad() {
		return proridad;
	}

	public void setProridad(byte proridad) {
		this.proridad = proridad;
	}

	public ArrayList<RankingEmpresa> calcular(ArrayList<RankingEmpresa> listEmpresas ){
		
		return listEmpresas;
	}
	
}
