package main.app;

import java.io.File;
import java.util.ArrayList;

import main.dataManagment.dataLoader.DataLoader;
import main.dataManagment.dataLoader.DataLoaderFactory;
import main.repositories.RepositorioEmpresa;
import model.Empresa;

import java.io.IOException;
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class DataFileManagement {
	static String CARPETANUEVASEMPRESAS = "/actualizacionesEmpresas/";
	static String CARPETAEMPRESASLEIDAS = "/empresasYaLeidas/";
	
	public static void main(String[] args) throws Exception {
		System.out.println("Iniciando programa...");
		
		RepositorioEmpresa repoEmpresa = RepositorioEmpresa.getInstance();
		DataLoader cargador = DataLoaderFactory.cargarData(DataLoaderFactory.ARCHIVO);
		String AbsolutePath = new File(".").getAbsolutePath();
		String directorioEmpresas = AbsolutePath + CARPETANUEVASEMPRESAS;
		String directorioEmpresasLeidas = AbsolutePath + CARPETAEMPRESASLEIDAS;
		
		File f = new File(directorioEmpresas);
		if (f.exists()){  
			File[] archivos = f.listFiles();
			for (int i=0;i<archivos.length;i++){
				try{
					repoEmpresa.cargarListaDeElementos(cargador.getDataEmpresas(directorioEmpresas + archivos[i].getName()));
					archivos[i].renameTo(new File(directorioEmpresasLeidas + archivos[i].getName()));
				}
				catch (Exception e){
					System.out.println("el archivo no pudo ser subido a la base");
				}
				
			}
		}
		else { 
			System.out.println("no existe");
		}
	}
	
	

	

}
