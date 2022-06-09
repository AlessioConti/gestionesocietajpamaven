package it.prova.gestionesocietajpamaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocietajpamaven.model.Societa;

public interface SocietaRepository extends CrudRepository<Societa, Long>, QueryByExampleExecutor<Societa>{
	
	@EntityGraph(attributePaths = { "dipendenti" })
	@Query("SELECT DISTINCT s FROM Societa s LEFT JOIN s.dipendenti d WHERE d.redditoAnnuoLordo >= 30000")
	List<Societa> findAllDistinctByDipendenti_RedditoAnnuoLordoGreaterThan();
	
}
