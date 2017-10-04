package repositories;

import org.uqbar.commons.utils.Observable;

import model.Metodologia;

@Observable
public class RepositorioMetodologia extends Repository<Metodologia> {

	private RepositorioMetodologia() {
		super(Metodologia.class);
	}

	private static RepositorioMetodologia repositorioMetodologia = null;

	public static RepositorioMetodologia getSingletonInstance() {

		if (repositorioMetodologia == null) {
			repositorioMetodologia = new RepositorioMetodologia();
		}

		return repositorioMetodologia;
	}

}
