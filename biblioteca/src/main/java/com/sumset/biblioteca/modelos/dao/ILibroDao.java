package com.sumset.biblioteca.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumset.biblioteca.modelos.entidades.Libro;

/**
 * Interface que hace uso de JPArepository para persistir la base de datos y algunas funcionalidades por defecto
 * @author Mateo Henao
 */
public interface ILibroDao extends JpaRepository<Libro,Long>{
	
	
	//Consulta para obtener todos los libros de la base de datos 
	@Query("from Libro")
	public List<Libro> findAllLibros();

}
