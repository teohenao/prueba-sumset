package com.sumset.biblioteca.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumset.biblioteca.modelos.entidades.Prestamo;

/**
 * Interface que hace uso de JPArepository para persistir la base de datos y algunas funcionalidades por defecto
 * @author Mateo Henao
 */
public interface IPrestamoDao extends JpaRepository<Prestamo,Long>{

	//consulta de todos los prestamos que se encuentran activos a la fecha
	@Query("select p from Prestamo p where p.estado=true")
	public List<Prestamo> findAllPrestamos();
	
	//consulta que determina la cantidad de libros prestados a la fecha
	@Query("select COUNT(p) from Prestamo p where p.estado=true")
	public int cantidadLibrosPrestados();
	
	//consulta que determina la cantidad de libros prestados a la fecha por sector
	@Query("select COUNT(p) from Prestamo p where p.estado=true and p.sector=?1")
	public int cantidadLibrosPrestadosSector(String sector);
	
}
