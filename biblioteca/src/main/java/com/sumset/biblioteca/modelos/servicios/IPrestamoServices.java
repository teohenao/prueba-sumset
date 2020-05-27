package com.sumset.biblioteca.modelos.servicios;

import java.util.List;

import com.sumset.biblioteca.modelos.entidades.Prestamo;

/**
 * Interface que representa todos los servicios que necesitamos implementar de la entidad prestamos
 * @author Mateo Henao
 */
public interface IPrestamoServices {

	//buscar un prestamo por id
	public Prestamo findById(Long id);

	//persistir un prestamo en la base de datos
	public Prestamo save(Prestamo prestamo);
	
	//listar todos los prestamos
	public List<Prestamo> findAllPrestamos();
	
	//obtener la cantidad de libros prestados a la fecha
	public int cantidadLibrosPrestados();
	
	//obtener la cantidad de libros prestados a la fecha por sector
	public int cantidadLibrosPrestadosSector(String sector);

	
}
