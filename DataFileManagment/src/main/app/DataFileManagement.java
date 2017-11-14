package main.app;

import java.io.File;

import main.dataManagment.dataLoader.DataLoader;
import main.dataManagment.dataLoader.DataLoaderFactory;
import main.repositories.Repository;
import model.Empresa;

public class DataFileManagement {
	static String CARPETANUEVASEMPRESAS = "/actualizacionesEmpresas/";
	static String CARPETAEMPRESASLEIDAS = "/empresasYaLeidas/";

	public static void main(String[] args) throws Exception {
		System.out.println("Iniciando programa...");

		Repository<Empresa> repoEmpresa = new Repository<Empresa>(Empresa.class);
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		String AbsolutePath = new File(".").getAbsolutePath();
		String directorioEmpresas = AbsolutePath + CARPETANUEVASEMPRESAS;
		String directorioEmpresasLeidas = AbsolutePath + CARPETAEMPRESASLEIDAS;

		File f = new File(directorioEmpresas);
		if (f.exists()) {
			File[] archivos = f.listFiles();
			for (int i = 0; i < archivos.length; i++) {
				try {
					repoEmpresa.cargarListaDeElementos(cargador
							.getDataEmpresas(directorioEmpresas
									+ archivos[i].getName()));
					archivos[i].renameTo(new File(directorioEmpresasLeidas
							+ archivos[i].getName()));
				} catch (Exception e) {
					System.out
							.println("El archivo "
									+ archivos[i].getName()
									+ " no pudo ser leido correctamente. Es probable que no tenga datos referidos a empresas, o que contenga algun error.");
				}

			}
		} else {
			System.out.println("no existe");
		}
	}
	
	

	

}
