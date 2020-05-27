package com.sumset.biblioteca.modelos.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumset.biblioteca.modelos.entidades.Usuario;

/**
 * Interface que hace uso de JPArepository para persistir la base de datos y algunas funcionalidades por defecto
 * @author Mateo Henao
 */
public interface IUsuarioDao extends JpaRepository<Usuario,Long> {
	
	//consylta que permite loggear a un usuario por medio de la indentificacion y la clave 
	@Query("select u from Usuario u where u.identificacion=?1 and u.clave=?2")
	public Usuario findByClaveAndIdentificacion(String identificacion,String clave);
	
}
