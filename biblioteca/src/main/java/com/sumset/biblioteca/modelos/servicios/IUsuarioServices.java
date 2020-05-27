package com.sumset.biblioteca.modelos.servicios;

import com.sumset.biblioteca.modelos.entidades.Usuario;

/**
 * Interface que representa todos los servicios que necesitamos implementar de la entidad usuarios
 * @author Mateo Henao
 */
public interface IUsuarioServices {

	//persistir un usuario en la base de datos
	public Usuario save(Usuario usuario);
	
	//buscar un usuario por el id en la base de datos
	public Usuario findById(Long id);
	
	//encontrar un usuario por medio de una identificacion y clave en la base de datos
	public Usuario findByClaveAndIdentificacion(String identificacion,String clave);
	
}
