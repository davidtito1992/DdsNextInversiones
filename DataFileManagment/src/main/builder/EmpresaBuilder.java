package main.builder;

import java.util.Objects;

import main.repositories.RepositorioEmpresa;
import main.repositories.RepositorioUsuario;
import model.Empresa;
import model.EmpresaModificacion;
import model.User;

public class EmpresaBuilder {

	public static Empresa build(EmpresaModificacion empMod) throws Exception{
		existeUser(empMod);
		
		return new Empresa(empMod.getNombre(),empMod.getPeriodos(),
				RepositorioUsuario.getSingletonInstance().getUser(empMod.getUser()));
	}
	
	public static void existeUser(EmpresaModificacion empMod) throws Exception{
		//El usuario no existe
		User usuario = RepositorioUsuario.getSingletonInstance().getUser(empMod.getUser());
		if (Objects.isNull(usuario))
			throw new Exception();

	}
}
