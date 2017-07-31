package indicadoresCondicionados;

import org.uqbar.commons.utils.Observable;

@Observable
public class RankingEmpresa {

	private int ranking;
	private String nombre;

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

}
