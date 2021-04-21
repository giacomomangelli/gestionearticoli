package it.gestionearticolijspservletjpamaven.dao;

public class MyDaoFactory {

	private static ArticoloDAO articoloDaoInstance = null;

	public static ArticoloDAO getArticoloDAOInstance() {
		if (articoloDaoInstance == null)
			articoloDaoInstance = new ArticoloDAOImpl();

		return articoloDaoInstance;
	}

}
