package it.prova.gestionesocietajpamaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocietajpamaven.model.Societa;

public interface SocietaRepository extends CrudRepository<Societa, Long>, QueryByExampleExecutor<Societa>{
	
	@EntityGraph(attributePaths = { "dipendenti" })
	@Query("from Societa s join s.dipendenti d where d.redditoAnnuoLordo > ?1")
	List<Societa> findAllDistinctByDipendenti_RedditoAnnuoLordoGreaterThan(int redditoAnnuo);
	
}
