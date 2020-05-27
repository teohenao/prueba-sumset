package com.sumset.biblioteca.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumset.biblioteca.modelos.entidades.Prestamo;

public interface IPrestamoDao extends JpaRepository<Prestamo,Long>{

	@Query("from Prestamo")
	public List<Prestamo> findAllPrestamos();
	
	@Query("select COUNT(p) from Prestamo p where p.estado=true")
	public int cantidadLibrosPrestados();
	
	@Query("select COUNT(p) from Prestamo p where p.estado=true and p.sector=?1")
	public int cantidadLibrosPrestadosSector(String sector);
	
}
