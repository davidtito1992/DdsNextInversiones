package main.app;

import java.io.File;

import main.dataManagment.dataLoader.FileLoader;
import main.repositories.RepositorioEmpresa;

public class DataFileManagement {
	static String CARPETAEMPRESAS = "/actualizacionesEmpresas/";
	
	public static void main(String[] args) throws Exception {
		System.out.println("Iniciando programa...");
		
		RepositorioEmpresa repoEmpresa = RepositorioEmpresa.getInstance();
		
		String AbsolutePath = new File(".").getAbsolutePath();
		String directorioEmpresas = AbsolutePath + CARPETAEMPRESAS;
		File f = new File(directorioEmpresas);
		if (f.exists()){  
			File[] ficheros = f.listFiles();
			for (int x=0;x<ficheros.length;x++){
				repoEmpresa.cargarListaDeElementos(new FileLoader().getDataEmpresas(directorioEmpresas + ficheros[x].getName()));
			}
		}
		else { 
			System.out.println("no existe");
		}
	}
	

}
