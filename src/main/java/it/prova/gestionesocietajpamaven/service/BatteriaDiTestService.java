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
		
		Societa societaTemp = new Societa("Nome "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
		societaService.inserisciNuovo(societaTemp);
		if(societaTemp.getId() == null || societaTemp.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");
		System.out.println("testInserisciNuovaSocieta concluso");
	}
	
	public void testFindSocietaByExample() {
		Long nowInMillisecondi = new Date().getTime();
		
		Societa societaTemp = new Societa("Nome "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
		societaService.inserisciNuovo(societaTemp);
		if(societaTemp.getId() == null || societaTemp.getId() < 1)
			throw new RuntimeException("testFindSocietaByExample...failed: inserimento primo elemento fallito");
		
		Societa societaTemp2 = new Societa("Nomignolo "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
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
		
		Societa societaTemp = new Societa("Nome "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
		societaService.inserisciNuovo(societaTemp);
		if(societaTemp.getId() == null || societaTemp.getId() < 1)
			throw new RuntimeException("testRimozioneSocieta...failed: inserimento primo elemento fallito");
		
		Societa societaTemp2 = new Societa("Nomignolo "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
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
		
		Societa societaTemp = new Societa("Nome "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
		societaService.inserisciNuovo(societaTemp);
		if(societaTemp.getId() == null || societaTemp.getId() < 1)
			throw new RuntimeException("listAllSocieta...failed: inserimento primo elemento fallito");
		
		Societa societaTemp2 = new Societa("Nomignolo "+nowInMillisecondi, nowInMillisecondi.toString(), new Date());
		
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
			nuovaSocieta = new Societa("Tentativo ",
					"Via Mosca, 10 " + nowInMillisecondi, new SimpleDateFormat("dd/MM/yyyy").parse("10/12/1970"));
			
			societaService.inserisciNuovo(nuovaSocieta);
			if(nuovaSocieta.getId() == 0 || nuovaSocieta.getId() < 1)
				throw new RuntimeException("test fallito");
			
			nowInMillisecondi++;
			
			nuovoDipendente = new Dipendente("Alessio", "Conti" + nowInMillisecondi, new SimpleDateFormat("dd/MM/yyyy").parse("04/28/2022"), 250000);
			nuovoDipendente.setSocieta(nuovaSocieta);
			
			dipendenteService.inserisciNuovo(nuovoDipendente);
			if (nuovoDipendente.getId() == null || nuovoDipendente.getId() < 1)
				throw new RuntimeException("testInserisciDipendente...failed: inserimento fallito");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("testInserisciDipendenti concluso...........");
	}
	
	public void testModificaDipendente() {

		Societa societaTemp = null;
		Dipendente dipendenteTemp = null;
		try {
			societaTemp = new Societa("Best Programming s.p.a. ", "Via Napoli 88 ", new SimpleDateFormat("dd/MM/yyyy").parse("20/01/2001"));
			
			societaService.inserisciNuovo(societaTemp);
			
			dipendenteTemp = new Dipendente("Mario", "Rossi", new SimpleDateFormat("dd/MM/yyyy").parse("03/05/1980"), 250000);
			dipendenteTemp.setSocieta(societaTemp);
			
			dipendenteService.inserisciNuovo(dipendenteTemp);
			if (dipendenteTemp.getId() == null || dipendenteTemp.getId() < 1)
				throw new RuntimeException("testInserisciDipendente...failed: inserimento fallito");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Dipendente dipendenteUpdate = dipendenteTemp;
		dipendenteUpdate.setNome("Luigi ");
		dipendenteUpdate.setCognome("Verdi ");
		
		dipendenteService.aggiorna(dipendenteUpdate);
		
		System.out.println("testModificaDipendente..................OK");
	}
}
