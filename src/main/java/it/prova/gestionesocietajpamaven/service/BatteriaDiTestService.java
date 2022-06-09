package it.prova.gestionesocietajpamaven.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocietajpamaven.model.Dipendente;
import it.prova.gestionesocietajpamaven.model.Societa;

@Service
public class BatteriaDiTestService {
	
	@Autowired
	private DipendenteService dipendenteService;
	
	@Autowired
	private SocietaService societaService;
	

	
	public void testInserisciNuovaSocieta() {
		Long nowInMillisecondi = new Date().getTime();
		
		Societa societaTemp = new Societa(1L, "Nome "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
		societaService.inserisciNuovo(societaTemp);
		if(societaTemp.getId() == null || societaTemp.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");
		System.out.println("testInserisciNuovaSocieta concluso");
	}
	
	public void testFindSocietaByExample() {
		Long nowInMillisecondi = new Date().getTime();
		
		Societa societaTemp = new Societa(2L, "Nome "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
		societaService.inserisciNuovo(societaTemp);
		if(societaTemp.getId() == null || societaTemp.getId() < 1)
			throw new RuntimeException("testFindSocietaByExample...failed: inserimento primo elemento fallito");
		
		Societa societaTemp2 = new Societa(3L, "Nomignolo "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
		societaService.inserisciNuovo(societaTemp2);
		if(societaTemp2.getId() == null || societaTemp2.getId() < 1)
			throw new RuntimeException("testFindSocietaByExample...failed: inserimento secondo elemento fallito");
		
		Societa societaRicerca = new Societa("Nomignolo");
		
		List<Societa> risultati = societaService.findByExample(societaRicerca);
		
		if(risultati.size() == 0)
			throw new RuntimeException("testFindSocietaByExample...failed: ricerca con nessun risultato prodotto");
		
		for(Societa societaItem : risultati)
			System.out.println(societaItem);
		
		System.out.println("testFindSocietaByExample concluso");
	}
	
	public void testRimozioneSocieta() {
		Long nowInMillisecondi = new Date().getTime();
		
		Societa societaTemp = new Societa(4L, "Nome "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
		societaService.inserisciNuovo(societaTemp);
		if(societaTemp.getId() == null || societaTemp.getId() < 1)
			throw new RuntimeException("testRimozioneSocieta...failed: inserimento primo elemento fallito");
		
		Societa societaTemp2 = new Societa(5L, "Nomignolo "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
		societaService.inserisciNuovo(societaTemp2);
		if(societaTemp2.getId() == null || societaTemp2.getId() < 1)
			throw new RuntimeException("testRimozioneSocieta...failed: inserimento secondo elemento fallito");
		
		try {
			societaService.removeConEccezione(societaTemp);
			throw new RuntimeException("testRimozioneSocieta...failed: eccezione non lanciata");
		}catch (Exception e) {
			// se passo di qui è tutto ok
		}
		
		System.out.println("testRimozioneSocieta concluso......");
	}
	
	public void listAllSocieta() {
		Long nowInMillisecondi = new Date().getTime();
		
		Societa societaTemp = new Societa(6L, "Nome "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
		societaService.inserisciNuovo(societaTemp);
		if(societaTemp.getId() == null || societaTemp.getId() < 1)
			throw new RuntimeException("listAllSocieta...failed: inserimento primo elemento fallito");
		
		Societa societaTemp2 = new Societa(7L, "Nomignolo "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
		societaService.inserisciNuovo(societaTemp2);
		if(societaTemp2.getId() == null || societaTemp2.getId() < 1)
			throw new RuntimeException("listAllSocieta...failed: inserimento secondo elemento fallito");
		
		List<Societa> lista = societaService.listAllSocieta();
		for(Societa societaItem : lista)
			System.out.println(societaItem);
	}
	
	public void testInserisciDipendenteInSocieta() {
		Long nowInMillisecondi = new Date().getTime();
		
		Societa nuovaSocieta = null;
		Dipendente nuovoDipendente = null;
		try {
			nuovaSocieta = new Societa(8L, "Tentativo ",
					"Via Mosca, 10 " + nowInMillisecondi, new SimpleDateFormat("dd/MM/yyyy").parse("10/12/1970"));
			
			societaService.inserisciNuovo(nuovaSocieta);
			
			nowInMillisecondi++;
			
			nuovoDipendente = new Dipendente(1L, "Alessio", "Conti" + nowInMillisecondi, new SimpleDateFormat("dd/MM/yyyy").parse("04/28/2022"), 250000);
			nuovoDipendente.setSocieta(nuovaSocieta);
			
			dipendenteService.inserisciNuovo(nuovoDipendente);
			if (nuovoDipendente.getId() == null || nuovoDipendente.getId() < 1)
				throw new RuntimeException("testInserisciDipendente...failed: inserimento fallito");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("testInserisciDipendenti concluso...........");
	}
}
