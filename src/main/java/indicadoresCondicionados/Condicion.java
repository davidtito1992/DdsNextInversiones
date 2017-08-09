package indicadoresCondicionados;

import java.math.BigDecimal;

import model.SnapshotIndicador;
import RankingEmpresa.RankingEmpresa;

@SuppressWarnings("unused")
public abstract class Condicion {
	
	private BigDecimal numeroAComparar;
	
	public abstract RankingEmpresa calcular(RankingEmpresa lista); 
	

}
