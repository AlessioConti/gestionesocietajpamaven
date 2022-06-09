package it.prova.gestionesocietajpamaven.service;

import java.util.List;

import it.prova.gestionesocietajpamaven.model.Societa;

public interface SocietaService {
	
	public List<Societa> listAllSocieta();
	
	public Societa caricaSingoloElemento(Long id);
	
	public void aggiorna(Societa societaInstance);
	
	public void inserisciNuovo(Societa input);
	
	public void rimuovi(Societa societaInstance);
	
	public List<Societa> findByExample(Societa example);
	
	public void removeConEccezione(Societa societaInstance);
	
	public List<Societa> cercaSocietaConDipendentiDalRedditoMaggioreDi30000();
}
