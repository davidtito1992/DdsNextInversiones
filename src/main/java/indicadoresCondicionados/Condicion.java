package indicadoresCondicionados;

import java.math.BigDecimal;

import model.SnapshotIndicador;

@SuppressWarnings("unused")
public abstract class Condicion {
	
	private BigDecimal numeroAComparar;
	
	public abstract SnapshotIndicador calcular(SnapshotIndicador lista); 
	

}
