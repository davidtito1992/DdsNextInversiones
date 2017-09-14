package model;

import java.util.List;
import java.util.stream.Collectors;

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

import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;
import RankingEmpresa.RankingEmpresasComparator;
import condiciones.Condicion;

@Entity
@SuppressWarnings("serial")
@Observable
@Table(name= "Metodologia")
@Transactional
public class Metodologia {

	public Metodologia(String nombre, List<Condicion> condiciones) {
		this.nombre = nombre;
		this.condiciones = condiciones;

	}

	/********* ATRIBUTOS *********/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "metodologiaId")
	private Long metodologiaId;
	
	@Column(name = "nombre")
	public String nombre;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "metodologia")
	public List<Condicion> condiciones;

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

	public List<RankingEmpresa> calcularEmpresas(List<RankingEmpresa> rEmpresas) {
		return rEmpresas.stream().map(empresa -> calcularEmpresa(empresa))
				.sorted(new RankingEmpresasComparator())
				.collect(Collectors.toList());
	}

}
