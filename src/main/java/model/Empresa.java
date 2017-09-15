package model;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@Entity
@Table(name= "Empresa")
@SuppressWarnings("serial")
@Transactional
@Observable
public class Empresa {

	/********* ATRIBUTOS *********/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empresaId")
	private Long empresaId;
	@Column(name = "nombre")
	private String nombre;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "empresa", cascade = CascadeType.ALL)
	private List<Periodo> periodos;

	/********* GETTERS/SETTERS *********/

	public Empresa() {

	}

	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}

	/********* METODOS *********/

	public int getAntiguedadEmpresa() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int anioActual = calendar.get(Calendar.YEAR);

		ArrayList<Year> aniosPeriodos = (ArrayList<Year>) this.getPeriodos().stream().map(periodo -> periodo.getAnio())
				.distinct().sorted().collect(Collectors.toCollection(ArrayList::new));

		return anioActual - aniosPeriodos.get(0).getValue();
	}

}
