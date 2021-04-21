package it.gestionearticolijspservletjpamaven.service;

import it.gestionearticolijspservletjpamaven.dao.ArticoloDAO;
import it.gestionearticolijspservletjpamaven.dao.ArticoloDAOImpl;

public class MyServiceFactory {

	// implementiamo il singleton in modo da evitare
	// proliferazione di riferimenti
	private static ArticoloService ARTICOLO_SERVICE_INSTANCE = null;
	private static ArticoloDAO ARTICOLODAO_INSTANCE = null;

	public static ArticoloService getArticoloServiceInstance() {
		if (ARTICOLO_SERVICE_INSTANCE == null)
			ARTICOLO_SERVICE_INSTANCE = new ArticoloServiceImpl();

		if (ARTICOLODAO_INSTANCE == null)
			ARTICOLODAO_INSTANCE = new ArticoloDAOImpl();

		ARTICOLO_SERVICE_INSTANCE.setArticoloDao(ARTICOLODAO_INSTANCE);

		return ARTICOLO_SERVICE_INSTANCE;
	}

}
