package com.sumset.biblioteca.modelos.servicios;

import java.util.List;

import com.sumset.biblioteca.modelos.entidades.Libro;

/**
 * Interface que representa todos los servicios que necesitamos implementar de la entidad libros
 * @author Mateo Henao
 */
public interface ILibroServices {
	
	//bucar un libro por id
	public Libro findById(Long id);

	//persistir un libro en la base de datos
	public Libro save(Libro libro);

	//listar todos los libros de la base de datos
	public List<Libro> findAllLibros();

}
