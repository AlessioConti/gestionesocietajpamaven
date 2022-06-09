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
			societaTemp = new Societa("Tentativo2", "Via Mosca, 12", new SimpleDateFormat("dd/MM/yyyy").parse("20/01/2001"));
			
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
	
	public void testSocietaConDipendentiConRedditoAPartireDa30000() {
		Long nowInMillisecondi = new Date().getTime();

		Societa nuovaSocieta = null;
		Societa nuovaSocieta2 = null;
		Societa nuovaSocieta3 = null;
		Dipendente nuovoDipendente = null;
		Dipendente nuovoDipendente2 = null;
		Dipendente nuovoDipendente3 = null;
		try {

			nuovaSocieta = new Societa("Solving Team",
					"Via Mosca, 14", new SimpleDateFormat("dd/MM/yyyy").parse("05/05/1989"));

			nuovaSocieta2 = new Societa("Solving Squad", "Via Mosca, 16",
					new SimpleDateFormat("dd/MM/yyyy").parse("18/09/1900"));

			nuovaSocieta3 = new Societa("Solving Legion", "Via Mosca, 18",
					new SimpleDateFormat("dd/MM/yyyy").parse("27/03/1980"));

			societaService.inserisciNuovo(nuovaSocieta);
			societaService.inserisciNuovo(nuovaSocieta2);
			societaService.inserisciNuovo(nuovaSocieta3);

			nowInMillisecondi++;

			nuovoDipendente = new Dipendente("Alessia", "Verini",
					new SimpleDateFormat("dd/MM/yyyy").parse("03/05/1980"), 25000);

			nuovoDipendente2 = new Dipendente("Giorgio", "Luca",
					new SimpleDateFormat("dd/MM/yyyy").parse("07/02/1978"), 36000);

			nuovoDipendente3 = new Dipendente("Sofia", "Arnese",
					new SimpleDateFormat("dd/MM/yyyy").parse("03/05/1999"), 31000);

			nuovoDipendente.setSocieta(nuovaSocieta);
			nuovoDipendente2.setSocieta(nuovaSocieta2);
			nuovoDipendente3.setSocieta(nuovaSocieta3);

			dipendenteService.inserisciNuovo(nuovoDipendente);
			dipendenteService.inserisciNuovo(nuovoDipendente2);
			dipendenteService.inserisciNuovo(nuovoDipendente3);
			if (nuovoDipendente.getId() == null || nuovoDipendente.getId() < 1 && nuovoDipendente2.getId() == null
					|| nuovoDipendente2.getId() < 1 || nuovoDipendente3.getId() == null || nuovoDipendente3.getId() < 1)
				throw new RuntimeException("testInserisciDipendente...failed: inserimento fallito");

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Societa> result = societaService.cercaSocietaConDipendentiDalRedditoMaggioreDi30000();

		result.forEach(item -> System.out.println(item));

		System.out.println("testSocietaConDipendentiConRalAPartireDa30000..................OK");
	}
}
