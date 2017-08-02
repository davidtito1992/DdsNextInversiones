package indicadoresCondicionados;

import org.uqbar.commons.utils.Observable;

@Observable
public class RankingEmpresa {

	private int ranking;
	private String nombre;
	private String observaciones ;

	public RankingEmpresa(int ranking, String nombre) {

		this.ranking = ranking;
		this.nombre = nombre;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	
}
