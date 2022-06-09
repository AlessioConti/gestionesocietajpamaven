package it.prova.gestionesocietajpamaven.service;

import java.util.List;

import it.prova.gestionesocietajpamaven.model.Dipendente;
import it.prova.gestionesocietajpamaven.model.Societa;

public interface DipendenteService {

	public List<Dipendente> listAllDipendenti();

	public Dipendente caricaSingoloElemento(Long id);

	public void aggiorna(Dipendente dipendenteInstance);

	public void inserisciNuovo(Dipendente dipendenteInstance);

	public void rimuovi(Dipendente dipendenteInstance);

	public List<Dipendente> trovaDipendentiPiuAnzianiDiSocietaFondatePrimaDei90();

	public void addTo(Dipendente dipendenteInstance, Societa societaInstance);
}
