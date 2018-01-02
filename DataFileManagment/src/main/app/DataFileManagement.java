package main.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import main.builder.EmpresaBuilder;
import main.dataManagment.dataLoader.DataLoader;
import main.dataManagment.dataLoader.DataLoaderFactory;
import main.repositories.RepositorioEmpresa;
import main.repositories.Repository;
import model.Empresa;
import model.EmpresaModificacion;


public class DataFileManagement {
	static String CARPETANUEVASEMPRESAS = "/actualizacionesEmpresas/";
	static String CARPETAEMPRESASLEIDAS = "/empresasYaLeidas/";

	public static void main(String[] args) throws Exception {
		System.out.println("Iniciando programa...");
		DataFileManagement dfm = new DataFileManagement();
		String AbsolutePath = new File(".").getAbsolutePath();
		String directorioEmpresas = AbsolutePath + CARPETANUEVASEMPRESAS;
		String directorioEmpresasLeidas = AbsolutePath + CARPETAEMPRESASLEIDAS;
//		Email.generateAndSendEmail("aaaa");
		
		try{
			dfm.guardarYEnviarAServer(directorioEmpresas);
			dfm.redireccionarArchivos(directorioEmpresas,directorioEmpresasLeidas);
		}
		catch (Exception e){
//			Email.generateAndSendEmail(e.getLocalizedMessage());//esta fallando el envio de mail
		}
	}
	

	
	public void redireccionarArchivos(String dEmpresas, String dEmpresasLeidas) throws Exception{
		File f = new File(dEmpresas);
		if (f.exists()) {
			File[] archivos = f.listFiles();
			for (int i = 0; i < archivos.length; i++) {
				try{
					archivos[i].renameTo(new File(dEmpresasLeidas + archivos[i].getName()));
				} catch (Exception e){
//					Email.generateAndSendEmail("El archivo " + archivos[i].getName()
//							+ " no pudo ser movido de directorio");
				}
			}
		}
	}

	public void guardarYEnviarAServer(String dEmpresas) throws Exception{
		NextAppExternalService na = new NextAppExternalService();
		DataLoader cargador = DataLoaderFactory.cargarData(DataLoaderFactory.ARCHIVO);
		List<Empresa> empresas = new ArrayList<Empresa>();
		
		File f = new File(dEmpresas);
		if (f.exists()) {
			File[] archivos = f.listFiles();
			for (int i = 0; i < archivos.length; i++) {
				try {
					cargador.getDataEmpresas(dEmpresas + archivos[i].getName())
							.forEach(empresaMod -> {
								Empresa empresa = EmpresaBuilder.build(empresaMod);
								if(!empresas.contains(empresa))
									empresas.add(empresa);
							});

				} catch (Exception e) {
//					Email.generateAndSendEmail("El archivo " + archivos[i].getName()
//							+ " no pudo ser leido correctamente. Es probable que no tenga datos referidos a empresas, o que contenga algun error.");
				}
			}
		} else {
//			Email.generateAndSendEmail("El directorio de archivos a leer es inexistente.");
		}
		
		empresas.forEach(empresa -> RepositorioEmpresa.getInstance().guardarOActualizar(empresa));
		na.enviarUsuariosARecalcular(new Gson().toJson(getUsuariosId(empresas)));
		
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
