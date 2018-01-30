package main.builder;

import java.util.Objects;

import main.repositories.RepositorioEmpresa;
import main.repositories.RepositorioUsuario;
import model.Empresa;
import model.EmpresaModificacion;
import model.User;

public class EmpresaBuilder {

	public static Empresa build(EmpresaModificacion empMod) throws Exception{
		validacionesEmpresaNueva(empMod);
		
		return new Empresa(empMod.getEmpresaId(),empMod.getNombre(),empMod.getPeriodos(),
				RepositorioUsuario.getSingletonInstance().getUser(empMod.getUser()));
	}
	
	public static void validacionesEmpresaNueva(EmpresaModificacion empMod) throws Exception{
		//El usuario no existe
		User usuario = RepositorioUsuario.getSingletonInstance().getUser(empMod.getUser());
		if (Objects.isNull(usuario))
			throw new Exception();
		
		//La empresa con ese id ya existe para otro usuario
		Empresa empresaOtroUser = RepositorioEmpresa.getInstance().getEmpresaOtroUser(empMod.getEmpresaId(), usuario.getEmail());
		if (!Objects.isNull(empresaOtroUser)){
			throw new Exception();
		}	
		
		//Este usuario ya tiene una empresa con este id y otro nombre
		Empresa empresaOtroNombre = RepositorioEmpresa.getInstance().getEmpresaIdUser(empMod.getEmpresaId(), usuario.getEmail());
		if (!Objects.isNull(empresaOtroNombre)){
			if (empresaOtroNombre.getNombre() != empMod.getNombre()){
				throw new Exception();
			}
		}
	}
}
