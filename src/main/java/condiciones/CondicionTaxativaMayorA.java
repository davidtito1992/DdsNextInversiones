package condiciones;

import parserIndicador.ParseException;
import app.DslIndicador;
import model.Empresa;
import model.RegistroIndicador;

public class CondicionTaxativaMayorA {
	private RegistroIndicador indicador;
	private int numeroAComparar;
	private int ultimosAnios;
	
	public CondicionTaxativaMayorA(RegistroIndicador indicador,int numeroAComparar,int ultimosAnios){
		this.indicador = indicador;
		this.numeroAComparar = numeroAComparar;
		this.ultimosAnios = ultimosAnios;
	}
	
	public boolean calcular(Empresa empresa) throws ParseException{
		DslIndicador dslIndicador = new DslIndicador();
		empresa.getPeriodos().stream()
		.filter(periodo -> periodo.getAnio().getValue() > 2017-ultimosAnios)
		.map(periodo -> dslIndicador.prepararFormula(this.indicador, empresa.getNombre(), periodo.getAnio(), periodo.getSemestre()).calcular());
		
		return false;
	}
}
