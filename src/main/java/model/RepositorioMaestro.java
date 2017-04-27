package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class RepositorioMaestro {

	public static Collection<Empresa> empresas = new ArrayList<Empresa>() ;
		 
//**********Aca deberia ir un parametro para filtrar?*************/	
	
	public static ArrayList<String> dameNombresEmpresas(){
		ArrayList<String> nombreEmpresas = new ArrayList<String>()  ;
		empresas.forEach(name-> (nombreEmpresas.add(name.getNombre()))) ;
		Collections.sort(nombreEmpresas);
		return nombreEmpresas;
}

	public static ArrayList<SnapshotEmpresa> dameSnapshotEmpresas(){
		ArrayList<SnapshotEmpresa> listSnapshot = new ArrayList<SnapshotEmpresa>() ;
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

//**********Aca deberia ir un parametro para filtrar?*************/	
	public static ArrayList<String> dameCuentasEmpresas(){
		
		HashSet<String> nombreCuentas = new HashSet<String>()  ;
		empresas.forEach(empresa->{
			empresa.getPeriodos().forEach(periodo->{
			periodo.getCuentas().forEach(cuenta-> nombreCuentas.add(cuenta.getNombre()));
			});
		});
		
		ArrayList<String> nombreCuentasfinal = new ArrayList<String>(nombreCuentas)  ;  
		Collections.sort(nombreCuentasfinal);
		return nombreCuentasfinal;
}
//**********Aca deberia ir un parametro para filtrar?*************/	
public static ArrayList<Integer> dameAniosPeriodos(){
		
		HashSet<Integer> cantidadAnios = new HashSet<Integer>()  ;
		empresas.forEach(empresa->{
			empresa.getPeriodos().forEach(periodo->{
				cantidadAnios.add(periodo.getAño());
			});
		    });
		ArrayList<Integer> nombreCuentasfinal = new ArrayList<Integer>(cantidadAnios)  ;  
		Collections.sort(nombreCuentasfinal);
		return nombreCuentasfinal;
}
	
}
