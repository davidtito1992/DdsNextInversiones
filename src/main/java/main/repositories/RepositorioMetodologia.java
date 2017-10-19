package main.repositories;

import model.Metodologia;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.uqbar.commons.utils.Observable;

import java.util.List;

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
