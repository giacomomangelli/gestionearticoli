package it.gestionearticolijspservletjpamaven.service;

import java.util.List;

import it.gestionearticolijspservletjpamaven.dao.ArticoloDAO;
import it.gestionearticolijspservletjpamaven.model.Articolo;

public interface ArticoloService {

	public void setArticoloDao(ArticoloDAO articoloDao);

	public List<Articolo> listAll() throws Exception;

	public Articolo caricaSingoloElemento(Long idInput) throws Exception;

	public void aggiorna(Articolo input) throws Exception;

	public void inserisciNuovo(Articolo input) throws Exception;

	public void rimuovi(Articolo input) throws Exception;

	public List<Articolo> findByExample(Articolo input) throws Exception;

}
