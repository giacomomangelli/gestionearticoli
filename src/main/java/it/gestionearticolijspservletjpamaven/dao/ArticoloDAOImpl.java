package it.gestionearticolijspservletjpamaven.dao;

import java.util.List;

import javax.persistence.EntityManager;

import it.gestionearticolijspservletjpamaven.model.Articolo;

public class ArticoloDAOImpl implements ArticoloDAO {

	private EntityManager entityManager;

	@Override
	public List<Articolo> list() throws Exception {
		return entityManager.createQuery("from Articolo", Articolo.class).getResultList();
	}

	@Override
	public Articolo findOne(Long id) throws Exception {
		if(id < 1) {
			throw new Exception("Errore inserimento nell'id.");
		}
		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo articoloInput) throws Exception {
		if(articoloInput == null) {
			throw new Exception("Problema valore in update input");
		}
		entityManager.merge(articoloInput);
	}

	@Override
	public void insert(Articolo articoloInput) throws Exception {
		if (articoloInput == null) {
			throw new Exception("Problema valore insert in input");
		}
		entityManager.persist(articoloInput);
	}

	@Override
	public void delete(Articolo articoloInput) throws Exception {
		if (articoloInput == null) {
			throw new Exception("Problema valore delete in input");
		}
		entityManager.remove(entityManager.merge(articoloInput));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
