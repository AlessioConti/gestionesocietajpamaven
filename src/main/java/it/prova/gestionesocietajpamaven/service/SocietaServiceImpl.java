package it.prova.gestionesocietajpamaven.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocietajpamaven.model.Societa;
import it.prova.gestionesocietajpamaven.repository.SocietaRepository;
@Service
public class SocietaServiceImpl implements SocietaService {

	@Autowired
	private SocietaRepository societaRepository;
	
	@Transactional(readOnly = true)
	public List<Societa> listAllSocieta() {
		return (List<Societa>)societaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Societa caricaSingoloElemento(Long id) {
		return societaRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Societa societaInstance) {
		societaRepository.save(societaInstance);
	}

	@Transactional
	public void inserisciNuovo(Societa input) {
		societaRepository.save(input);
	}

	@Transactional
	public void rimuovi(Societa societaInstance) {
		societaRepository.delete(societaInstance);
	}

	@Override
	public List<Societa> findByExample(Societa example) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withStringMatcher(StringMatcher.CONTAINING);
		return (List<Societa>)societaRepository.findAll(Example.of(example, matcher));
	}

	@Override
	public void removeConEccezione(Societa societaInstance) {
		societaRepository.delete(societaInstance);
		throw new RuntimeException("Eccezione di prova transazione");
	}

	@Override
	public List<Societa> cercaSocietaConDipendentiDalRedditoMaggioreDi(int redditoControllo) {
		return societaRepository.findAllDistinctByDipendenti_RedditoAnnuoLordoGreaterThan(redditoControllo);
	}

}
