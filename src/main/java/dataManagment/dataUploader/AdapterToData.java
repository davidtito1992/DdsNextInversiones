package dataManagment.dataUploader;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

interface AdapterToData {

	public String getStringRegistroIndicador(RegistroIndicador unIndicador) ;

	public String getStringEmpresa(Empresa unaEmpresa);

	public String getStringMetodologia(Metodologia unaMetodologia);

}
