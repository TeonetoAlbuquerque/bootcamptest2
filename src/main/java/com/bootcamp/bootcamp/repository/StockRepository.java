package com.bootcamp.bootcamp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bootcamp.bootcamp.model.Stocker;



@Repository
public interface StockRepository extends JpaRepository<Stocker,Long>{

	Optional<Stocker> findByNameAndDate(String name, LocalDate date);


	@Query("SELECT stock " +
			 " FROM Stocker stock " + 
			 " WHERE stock.name = :name AND stock.date = :date AND stock.id <> :Id")
	Optional<Stocker> findByStockUpdate(String name, LocalDate date, Long Id);
	
	

	
	@Query("SELECT stock " +
			 " FROM Stocker stock " + 
			 " WHERE stock.date = :date")
	Optional<List<Stocker>>findByToday(LocalDate date);

}
