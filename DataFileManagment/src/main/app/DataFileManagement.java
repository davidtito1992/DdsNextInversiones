package main.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import main.dataManagment.dataLoader.DataLoader;
import main.dataManagment.dataLoader.DataLoaderFactory;
import main.repositories.Repository;
import model.Empresa;


public class DataFileManagement {
	static String CARPETANUEVASEMPRESAS = "/actualizacionesEmpresas/";
	static String CARPETAEMPRESASLEIDAS = "/empresasYaLeidas/";

	public static void main(String[] args) throws Exception {
		System.out.println("Iniciando programa...");
		DataFileManagement dfm = new DataFileManagement();
		NextAppExternalService nextApp = new NextAppExternalService();
		
		String AbsolutePath = new File(".").getAbsolutePath();
		String directorioEmpresas = AbsolutePath + CARPETANUEVASEMPRESAS;
		String directorioEmpresasLeidas = AbsolutePath + CARPETAEMPRESASLEIDAS;
		final Gson gson = new Gson();
		
		List<Long> usuariosId = dfm.leerYGuardarArchivos(directorioEmpresas);
		nextApp.enviarUsuariosARecalcular(gson.toJson(usuariosId));
		dfm.redireccionarArchivos(directorioEmpresas,directorioEmpresasLeidas);
	}
	
	public void redireccionarArchivos(String dEmpresas, String dEmpresasLeidas){
		File f = new File(dEmpresas);
		if (f.exists()) {
			File[] archivos = f.listFiles();
			for (int i = 0; i < archivos.length; i++) {
				archivos[i].renameTo(new File(dEmpresasLeidas + archivos[i].getName()));
			}
		}
	}

	public List<Long> leerYGuardarArchivos(String dEmpresas){
		Repository<Empresa> repoEmpresa = new Repository<Empresa>(Empresa.class);
		DataLoader cargador = DataLoaderFactory.cargarData(DataLoaderFactory.ARCHIVO);
		List<Empresa> empresas = new ArrayList<Empresa>();
		
		File f = new File(dEmpresas);
		if (f.exists()) {
			File[] archivos = f.listFiles();
			for (int i = 0; i < archivos.length; i++) {
				try {
					cargador.getDataEmpresas(dEmpresas + archivos[i].getName())
							.forEach(empresa -> {
								if(!empresas.contains(empresa))
									empresas.add(empresa);
				});

				} catch (Exception e) {
					System.out.println("El archivo " + archivos[i].getName()
							+ " no pudo ser leido correctamente. Es probable que no tenga datos referidos a empresas, o que contenga algun error.");
				}
			}
		} else {
			System.out.println("El directorio de archivos a leer es inexistente.");
		}
		repoEmpresa.cargarListaDeElementos(empresas);
		return getUsuariosId(empresas);
	}
	
	public static List<Long> getUsuariosId(List<Empresa> empresas) {
		List<Long> listaIds = new ArrayList<Long>();
		empresas.forEach(empresa -> {
			if (!listaIds.contains(empresa.getUser().getUserId()))
				listaIds.add(empresa.getUser().getUserId());
		});
		return listaIds;
	}

}
