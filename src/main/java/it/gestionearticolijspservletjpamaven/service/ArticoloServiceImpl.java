package it.gestionearticolijspservletjpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.gestionearticolijspservletjpamaven.dao.ArticoloDAO;
import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.web.listener.LocalEntityManagerFactoryListener;

public class ArticoloServiceImpl implements ArticoloService {

	private ArticoloDAO articoloDao;

	public void setArticoloDao(ArticoloDAO articoloDao) {
		this.articoloDao = articoloDao;
	}

	@Override
	public List<Articolo> listAll() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			articoloDao.setEntityManager(entityManager);

			return articoloDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Articolo caricaSingoloElemento(Long idInput) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			articoloDao.setEntityManager(entityManager);

			return articoloDao.findOne(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Articolo articoloInput) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			articoloDao.setEntityManager(entityManager);
			
			articoloDao.update(articoloInput);
			
			entityManager.getTransaction().commit();
		}catch(Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		}finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Articolo articoloInput) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			articoloDao.setEntityManager(entityManager);

			articoloDao.insert(articoloInput);

			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
			
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Articolo articoloInput) throws Exception {
		
		if(articoloInput == null) {
			throw new Exception("Errore input rimozione service.");
		}
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			articoloDao.setEntityManager(entityManager);

			entityManager.merge(articoloInput);
			articoloDao.delete(articoloInput);
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
			
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Articolo> findByExample(Articolo input) throws Exception {
		throw new Exception("Funzionalita' non disponibile.");
	}

}
