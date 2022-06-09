package it.prova.gestionesocietajpamaven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionesocietajpamaven.service.BatteriaDiTestService;

@SpringBootApplication
public class GestionesocietajpamavenApplication implements CommandLineRunner{
	
	@Autowired
	private BatteriaDiTestService batteriaDiTestService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionesocietajpamavenApplication.class, args);
	}
	
	public void run(String...args) throws Exception{
		System.out.println("################ START   #################");
		System.out.println("################ eseguo i test  #################");
		
		batteriaDiTestService.listAllSocieta();
		batteriaDiTestService.testInserisciNuovaSocieta();
		
		System.out.println("################ FINE   #################");
	}

}
