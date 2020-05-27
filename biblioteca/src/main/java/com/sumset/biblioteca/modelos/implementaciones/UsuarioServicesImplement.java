package com.sumset.biblioteca.modelos.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumset.biblioteca.modelos.dao.IUsuarioDao;
import com.sumset.biblioteca.modelos.entidades.Usuario;
import com.sumset.biblioteca.modelos.servicios.IUsuarioServices;

/**
 * servicio que implementa los servicios de la interfaz y el repositorio por defecto de la entiad usuario
 * @author Mateo Henao
 */
@Service
public class UsuarioServicesImplement implements IUsuarioServices {
  
	//inyectamos el repositorio de usuarios
	@Autowired
	private IUsuarioDao usuarioDao;
	
	//metodo que permite guardar un nuevo usuario si llegado al caso es necesario
	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	//metodo que permite loggear a un usuario por medio de la identificacion y una clave
	@Override
	@Transactional(readOnly = true)
	public Usuario findByClaveAndIdentificacion(String identificacion, String clave) {
		return usuarioDao.findByClaveAndIdentificacion(identificacion, clave);
	}
	
	//metodo que permite buscar a un determinado usuario por el id
	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}
}