package com.sumset.biblioteca.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumset.biblioteca.modelos.entidades.Libro;

public interface ILibroDao extends JpaRepository<Libro,Long>{
	
	@Query("from Libro")
	public List<Libro> findAllLibros();

}
