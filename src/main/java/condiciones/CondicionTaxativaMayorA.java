package condiciones;

import model.Empresa;

public class CondicionTaxativaMayorA {
	private String indicador;
	private int numeroAComparar;
	private int ultimosAnios;
	
	public CondicionTaxativaMayorA(String indicador,int numeroAComparar,int ultimosAnios){
		this.indicador = indicador;
		this.numeroAComparar = numeroAComparar;
		this.ultimosAnios = ultimosAnios;
	}
	
	public boolean calcular(Empresa empresa){
		empresa.getPeriodos().stream().filter(periodo -> periodo.getAnio().getValue() > 2017-ultimosAnios );
		
		return false;
	}
}
