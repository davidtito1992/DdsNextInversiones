package main.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import main.builder.EmpresaBuilder;
import main.dataManagment.dataLoader.DataLoader;
import main.dataManagment.dataLoader.DataLoaderFactory;
import main.repositories.RepositorioEmpresa;
import model.Empresa;


public class DataFileManagement {
	static String CARPETANUEVASEMPRESAS = "/home/2017-vn-group-20/DataFileManagment/actualizacionesEmpresas/";
	static String CARPETAEMPRESASLEIDAS = "/home/2017-vn-group-20/DataFileManagment/empresasYaLeidas/";
	static String DIRECTORIOEMPRESAS = new File(".").getAbsolutePath() + CARPETANUEVASEMPRESAS;
	static String DIRECTORIOEMPRESASLEIDAS = new File(".").getAbsolutePath() + CARPETAEMPRESASLEIDAS;
	
	public static void main(String[] args) throws Exception {
		System.out.println("Iniciando programa...");
		DataFileManagement dfm = new DataFileManagement();
		NextAppExternalService na = new NextAppExternalService();
		try{
			List<Long> usuariosARecalcular = dfm.actualizarEmpresas();
			if (!usuariosARecalcular.isEmpty())
				na.enviarUsuariosARecalcular(new Gson().toJson(usuariosARecalcular));
		}
		catch (Exception e){
//			Email.generateAndSendEmail(e.getLocalizedMessage());//esta fallando el envio de mail
			e.printStackTrace();
		}
		System.out.println("Finaliza programa.");
	}
	

	
	public void redireccionarArchivo(File archivo) {
		try {
			archivo.renameTo(new File(DIRECTORIOEMPRESASLEIDAS
					+ archivo.getName()));
		} catch (Exception e) {
//			Email.generateAndSendEmail("El archivo " + archivo.getName() + " no pudo ser redireccionado, aunque fue leido correctamente.");
		}
	}

	public List<Long> actualizarEmpresas() throws Exception{
		DataLoader cargador = DataLoaderFactory.cargarData(DataLoaderFactory.ARCHIVO);
		List<Empresa> empresas = new ArrayList<Empresa>();
		
		File f = new File(DIRECTORIOEMPRESAS);
		if (f.exists()) {
			File[] archivos = f.listFiles();
			for (int i = 0; i < archivos.length; i++) {
				try {
					cargador.getDataEmpresas(DIRECTORIOEMPRESAS + archivos[i].getName())
							.forEach(empresaMod -> {
								try{
									empresas.add(EmpresaBuilder.build(empresaMod));
								} catch (Exception e) {
									//Email.generateAndSendEmail("El usuario no existe");
								}
							});
					redireccionarArchivo(archivos[i]);
				} catch (Exception e) {
//					Email.generateAndSendEmail("El archivo " + archivos[i].getName() + " no pudo ser leido correctamente.");
				}
			}
		} else {
//			Email.generateAndSendEmail("El directorio de archivos a leer es inexistente.");
		}
		
		empresas.forEach(empresa -> RepositorioEmpresa.getInstance().Actualizar(empresa));
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
