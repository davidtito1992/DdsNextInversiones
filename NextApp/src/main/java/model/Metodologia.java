package model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import main.condiciones.Condicion;
import main.parserIndicador.ParseException;
import main.rankingEmpresa.RankingEmpresa;
import main.rankingEmpresa.RankingEmpresasComparator;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@Entity
@Observable
@Table(name = "Metodologias")
@Transactional
public class Metodologia {

	public Metodologia(String nombre, List<Condicion> condiciones, User user) {
		this.nombre = nombre;
		this.condiciones = condiciones;
		this.user = user;
	}

	public Metodologia() {
	}

	public Metodologia(String nombre, List<Condicion> condiciones) {
		this.nombre = nombre;
		this.condiciones = condiciones;
	}

	/********* ATRIBUTOS *********/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long metodologiaId;

	private String nombre;

	@JoinColumn
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Condicion> condiciones;

	/********* GETTERS/SETTERS *********/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getMetodologiaId() {
		return metodologiaId;
	}

	/********* METODOS *********/

	public List<RankingEmpresa> calcularEmpresas(List<RankingEmpresa> rEmpresas) {
		return rEmpresas.stream().map(empresa -> calcularEmpresa(empresa))
				.sorted(new RankingEmpresasComparator())
				.collect(Collectors.toList());
	}

	public RankingEmpresa calcularEmpresa(RankingEmpresa rEmpresa) {
		try {
			for (Condicion condicion : condiciones) {
				rEmpresa = condicion.calcular(rEmpresa);
			}
		} catch (ParseException e) {
			rEmpresa.setErrorTaxativa(true);
			rEmpresa.setObservaciones(e.getMessage());
		} catch (RuntimeException e) {
			rEmpresa.setErrorTaxativa(true);
			rEmpresa.setObservaciones(e.getMessage());
		}
		return rEmpresa;
	}

	public String getUrl() {
		return this.metodologiaId.toString();
	}

}
