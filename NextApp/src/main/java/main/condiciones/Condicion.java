package main.condiciones;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import main.app.DslIndicador;
import main.parserIndicador.ParseException;
import main.rankingEmpresa.RankingEmpresa;
import model.Empresa;
import model.Periodo;
import model.PeriodoComparator;
import model.RegistroIndicador;

@Entity
@Table(name = "Condiciones")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoCondicion", discriminatorType = DiscriminatorType.STRING)
public abstract class Condicion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long condicionId;

	protected int ultimosAnios;

	public abstract RankingEmpresa calcular(RankingEmpresa rEmpresa) throws ParseException;

	protected List<Periodo> periodosDesdexAnio(Empresa empresa) {
		Year anioLimite = Year.of(LocalDate.now().getYear() - ultimosAnios);

		return empresa.getPeriodos().stream().filter(periodo -> periodo.getAnio().isAfter(anioLimite))
				.sorted(new PeriodoComparator()).collect(Collectors.toList());

	}

	protected BigDecimal aplicarIndicador(RegistroIndicador indicador, String nombreEmpresa, Year anio, int semestre)
			throws ParseException {
		return new DslIndicador().prepararFormula(indicador, nombreEmpresa, anio, semestre).calcular();
	}
}
