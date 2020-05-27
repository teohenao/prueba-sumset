package com.sumset.biblioteca.modelos.implementaciones;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.sumset.biblioteca.modelos.dao.ILibroDao;
import com.sumset.biblioteca.modelos.entidades.Libro;
import com.sumset.biblioteca.modelos.servicios.ILibroServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class LibroServicesImplement implements ILibroServices {

	@Autowired
	private ILibroDao libroDao;
	
	@Override
	@Transactional
	public Libro save(Libro libro) {
		return libroDao.save(libro);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Libro findById(Long id) {
		return libroDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Libro> findAllLibros() {
		return libroDao.findAllLibros();
	}


	
}
