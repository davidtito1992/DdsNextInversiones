package model;

import java.util.Comparator;

public class PeriodoComparator implements Comparator<Periodo>{

	public int compare(Periodo periodo1, Periodo periodo2) {
		int anio1 = periodo1.getAnio().getValue();
		int anio2 = periodo2.getAnio().getValue();
		int semestre1 = periodo1.getSemestre();
		int semestre2 = periodo2.getSemestre();
		if(anio1>anio2){
			return 1;
		}else{
			if(anio1<anio2){
				return -1;
			}else{
				if(semestre1>semestre2){
					return 1;
				}else{
					if(semestre1<semestre2){
					return -1;
					}else {
						return 0;
					}
				}
			}
		}
	}

}
