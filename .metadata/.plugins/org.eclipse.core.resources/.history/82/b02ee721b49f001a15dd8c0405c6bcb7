package com.sumset.biblioteca.modelos.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumset.biblioteca.modelos.entidades.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario,Long> {
	
	@Query("select u from Usuario u where u.identificacion=?1 and u.clave=?2")
	public Usuario findByClaveAndIdentificacion(String identificacion,String clave);
	
}
