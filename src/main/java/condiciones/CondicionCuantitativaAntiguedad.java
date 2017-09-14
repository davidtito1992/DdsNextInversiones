package condiciones;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

@Entity
@DiscriminatorValue("2")
public class CondicionCuantitativaAntiguedad extends Condicion {

	public CondicionCuantitativaAntiguedad() {

	}
	
	@Column(name = "peso")
	private BigDecimal peso;

	public CondicionCuantitativaAntiguedad(BigDecimal peso) {
		this.peso = peso;
	}

	public RankingEmpresa calcular(RankingEmpresa rEmpresa)
			throws ParseException {
		BigDecimal nuevoValor = new BigDecimal(rEmpresa.dameAntiguedadEmpresa())
				.multiply(peso);
		rEmpresa.acumularValor(nuevoValor);
		return rEmpresa;
	}

}
