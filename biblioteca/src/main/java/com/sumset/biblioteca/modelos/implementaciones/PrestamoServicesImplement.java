package com.sumset.biblioteca.modelos.implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumset.biblioteca.modelos.dao.IPrestamoDao;
import com.sumset.biblioteca.modelos.entidades.Prestamo;
import com.sumset.biblioteca.modelos.servicios.IPrestamoServices;

/**
 * servicio que implementa los servicios de la interfaz y el repositorio por defecto de la entiad prestamo
 * @author Mateo Henao
 */
@Service
public class PrestamoServicesImplement implements IPrestamoServices{

    //inyectamos el repositorio de la entidad	
	@Autowired
	private IPrestamoDao prestamoDao;
	
	//metodo que permite buscar por medio de un id un prestamo
	@Override
	public Prestamo findById(Long id) {
		return prestamoDao.findById(id).orElse(null);
	}

	//metodo que permite guardar en la base de datos un prestamo
	@Override
	public Prestamo save(Prestamo prestamo) {
		return prestamoDao.save(prestamo);
	}
	
	//metodo que permite buscar todos los prestamos de la base de datos
	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> findAllPrestamos() {
		return prestamoDao.findAllPrestamos();
	}

	//metodo que permite obtener la cantidad de libros prestados a la fecha
	@Override
	public int cantidadLibrosPrestados() {
		return prestamoDao.cantidadLibrosPrestados();
	}

	//metodo que permite obtener la cantidad de libros prestados a la fecha por determinado sector
	@Override
	public int cantidadLibrosPrestadosSector(String sector) {
		return prestamoDao.cantidadLibrosPrestadosSector(sector);
	}

}
