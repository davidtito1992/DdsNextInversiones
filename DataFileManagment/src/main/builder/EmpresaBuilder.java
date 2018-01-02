package main.builder;

import main.repositories.RepositorioUsuario;
import model.Empresa;
import model.EmpresaModificacion;

public class EmpresaBuilder {

	public static Empresa build(EmpresaModificacion empMod){
		
		return new Empresa(empMod.getEmpresaId(),empMod.getNombre(),empMod.getPeriodos(),
					RepositorioUsuario.getSingletonInstance().getUser(empMod.getUser()));
	}
}
