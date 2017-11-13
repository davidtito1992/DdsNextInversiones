package main.repositories;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.uqbar.commons.utils.Observable;

import model.User;

@Observable
public class RepositorioUsuario extends Repository<User> {

	private RepositorioUsuario() {
		super(User.class);
	}

	private static RepositorioUsuario repositorioUsuario = null;

	public static RepositorioUsuario getSingletonInstance() {

		if (repositorioUsuario == null) {
			repositorioUsuario = new RepositorioUsuario();
		}

		return repositorioUsuario;
	}

	public User getUser(String mailUsuario) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(User.class)
				.add(Restrictions.eq("email", mailUsuario));
		return (User) criteria.uniqueResult();
	}

}
