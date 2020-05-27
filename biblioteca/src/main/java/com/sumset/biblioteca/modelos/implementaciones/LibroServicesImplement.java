package com.sumset.biblioteca.modelos.implementaciones;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.sumset.biblioteca.modelos.dao.ILibroDao;
import com.sumset.biblioteca.modelos.entidades.Libro;
import com.sumset.biblioteca.modelos.servicios.ILibroServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * servicio que implementa los servicios de la interfaz y el repositorio por defecto de la entiad libro
 * @author Mateo Henao
 */
@Service
public class LibroServicesImplement implements ILibroServices {

	//inyectamos el repositorio de la entidad
	@Autowired
	private ILibroDao libroDao;
	
	//metodo que permite guardar en la base de datos un libro
	@Override
	@Transactional
	public Libro save(Libro libro) {
		return libroDao.save(libro);
	}
	
	//metodo que permite buscar por medio del id un libro
	@Override
	@Transactional(readOnly = true)
	public Libro findById(Long id) {
		return libroDao.findById(id).orElse(null);
	}
	
	//metodo que permite buscar todos los libros de la base de datos
	@Override
	@Transactional(readOnly = true)
	public List<Libro> findAllLibros() {
		return libroDao.findAllLibros();
	}


	
}
