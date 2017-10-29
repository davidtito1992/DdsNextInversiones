package service;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import main.repositories.RepositorioEmpresa;
import model.Empresa;
import model.SnapshotEmpresa;

public class EmpresaService {

	public static HashMap<String, Object> armarHashMap(Long idUsuario, String cuentaSeleccionada,
			String nombreSeleccionado, Year anioSeleccionado, Integer semestreSeleccionado) {
		HashMap<String, Object> mapEmpresas = new HashMap<>();

		List<Empresa> todasLasEmpresasDelUsuario = RepositorioEmpresa.getInstance().allInstancesUser(idUsuario);
		List<Empresa> empresasPorFiltros = idUsuario != null
				? RepositorioEmpresa.getInstance().filtrar(cuentaSeleccionada, nombreSeleccionado, semestreSeleccionado,
						anioSeleccionado, idUsuario)
				: new ArrayList<>();
		List<SnapshotEmpresa> empresas = RepositorioEmpresa.getInstance().dameSnapshotEmpresas(empresasPorFiltros,
				nombreSeleccionado, cuentaSeleccionada, anioSeleccionado, semestreSeleccionado);

		mapEmpresas.put("empresas", empresas);
		mapEmpresas.put("listaVacia", empresas.isEmpty());
		mapEmpresas.put("nombresCuentas",
				RepositorioEmpresa.getInstance().todosLosNombresDeCuentas(todasLasEmpresasDelUsuario));
		mapEmpresas.put("nombresEmpresas",
				RepositorioEmpresa.getInstance().todosLosNombresDeEmpresas(todasLasEmpresasDelUsuario));
		mapEmpresas.put("anios", RepositorioEmpresa.getInstance().todosLosAnios(todasLasEmpresasDelUsuario));
		mapEmpresas.put("periodos", RepositorioEmpresa.getInstance().todosLosPeriodos(todasLasEmpresasDelUsuario));
		mapEmpresas.put("empresaSeleccionada",nombreSeleccionado);
		mapEmpresas.put("cuentaSeleccionada",cuentaSeleccionada);
		mapEmpresas.put("anioSeleccionado",anioSeleccionado);
		mapEmpresas.put("semestreSeleccionado",semestreSeleccionado);

		return mapEmpresas;
	}

	public static HashMap<String, Object> homeView(String cuenta, String empresa, String anio, String semestre,
			Long idUsuario) {
		String cuentaSeleccionada = Objects.isNull(cuenta) || cuenta.isEmpty() ? null : cuenta;
		String nombreSeleccionado = Objects.isNull(empresa) || empresa.isEmpty() ? null : empresa;
		Year anioSeleccionado = Objects.isNull(anio) || anio.isEmpty() ? null : Year.parse(anio);
		Integer semestreSeleccionado = Objects.isNull(semestre) || semestre.isEmpty() ? null
				: Integer.parseInt(semestre);

		HashMap<String, Object> mapEmpresas = armarHashMap(idUsuario, cuentaSeleccionada, nombreSeleccionado,
				anioSeleccionado, semestreSeleccionado);

		return mapEmpresas;
	}

}
