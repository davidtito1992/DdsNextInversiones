package RankingEmpresa;

import java.math.BigDecimal;
import java.util.Comparator;

public class RankingEmpresasComparator implements Comparator<RankingEmpresa>{

	@Override
	public int compare(RankingEmpresa r1, RankingEmpresa r2) {
		BigDecimal ranking1 = r1.getRanking();
		BigDecimal ranking2 = r2.getRanking();
		
	       if (ranking1.compareTo(ranking2) < 0) {
	           return 1;
	       } else if (ranking1.compareTo(ranking2) > 0){
	           return -1;
	       } else {
	           return 0;
	       }

	}


}
