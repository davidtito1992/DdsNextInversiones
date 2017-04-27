package model;

import java.util.ArrayList;
import java.util.Collection;

public class RepositorioMaestro {

	public static Collection<Empresa> empresas = new ArrayList<Empresa>() ;
	
	 
	
	public static ArrayList<String> dameNombresEmpresas(){
		ArrayList<String> nombreEmpresas = new ArrayList<String>()  ;
		empresas.forEach(name-> (nombreEmpresas.add(name.getNombre()))) ;
    
		return nombreEmpresas;
}

	
	public static Collection<SnapshotEmpresa> dameSnapshotEmpresas(){
		Collection<SnapshotEmpresa> listSnapshot = new ArrayList<SnapshotEmpresa>() ;
		empresas.forEach(empresa->{
			empresa.getPeriodos().forEach(periodo->{
				periodo.getCuentas().forEach(cuenta->{
					SnapshotEmpresa snapshotempresa = new SnapshotEmpresa();
					snapshotempresa.setCuenta(cuenta.getNombre());
					snapshotempresa.setValor(cuenta.getValor());
					snapshotempresa.setNombre(empresa.getNombre());
					snapshotempresa.setSemestre(periodo.getSemestre());
					snapshotempresa.setAño(periodo.getAño());
					listSnapshot.add(snapshotempresa);
				});
			});
		});
		return listSnapshot;
	}
}
