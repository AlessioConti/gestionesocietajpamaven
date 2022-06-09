package it.prova.gestionesocietajpamaven.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocietajpamaven.model.Dipendente;
import it.prova.gestionesocietajpamaven.model.Societa;
import it.prova.gestionesocietajpamaven.repository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {

	@Autowired
	private DipendenteRepository dipendenteRepository;
	
	@Transactional(readOnly = true)
	public List<Dipendente> listAllDipendenti() {
		return (List<Dipendente>)dipendenteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Dipendente caricaSingoloElemento(Long id) {
		return dipendenteRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Dipendente dipendenteInstance) {
		dipendenteRepository.save(dipendenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		dipendenteRepository.save(dipendenteInstance);
	}

	@Transactional
	public void rimuovi(Dipendente dipendenteInstance) {
		dipendenteRepository.delete(dipendenteInstance);
	}

	@Override
	public List<Dipendente> trovaDipendentiPiuAnzianiDiSocietaFondatePrimaDei90() {
		return dipendenteRepository.findFirstBySocieta_DataFondazioneLowerThan1990OrderByDataAssunzioneDesc();
	}

	@Override
	public void addTo(Dipendente dipendenteInstance, Societa societaInstance) {
		dipendenteInstance.setSocieta(societaInstance);
		dipendenteRepository.save(dipendenteInstance);
	}

}
