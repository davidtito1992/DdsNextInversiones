package main.repositories;

import model.Metodologia;

import org.uqbar.commons.utils.Observable;

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
