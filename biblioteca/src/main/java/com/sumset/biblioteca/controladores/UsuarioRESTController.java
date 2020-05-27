package com.sumset.biblioteca.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sumset.biblioteca.modelos.entidades.Usuario;
import com.sumset.biblioteca.modelos.servicios.IUsuarioServices;


/**
 * servicio Rest que hace uso de los repositorios para  los usuarios
 * @author Mateo Henao
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/biblioteca")
public class UsuarioRESTController {

	//inyectamos los servicios del usuario
	@Autowired
	private IUsuarioServices usuarioServicio;
	
    /**
     * Metodo POST para realizar el registro de un usuario
     * @param usuario
     * @param result
     */
	@PostMapping("/usuarios")
	public ResponseEntity<?> agregarUsuario(@Valid @RequestBody Usuario usuario,BindingResult result)
	{
		Usuario nuevoUsuario = null;
		Map<String, Object> response  = new HashMap<>();
		if(result.hasErrors())
		{
		 List<String> errors = result.getFieldErrors()
				 .stream().map(err->{
					 return "El campo"+err.getField()+" "+err.getDefaultMessage();
				 }).collect(Collectors.toList());
		 response.put("errors", errors);
		 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			nuevoUsuario = usuarioServicio.save(usuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrio un error en la bd");
			response.put("mensaje", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "usuario creado con exito");
		response.put("usuario", nuevoUsuario);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	/**
     * Metodo GET para  loggear usuario por medio de la identificacion y la clave
     * @param identificacion
     * @param clave
     */
	@GetMapping("/usuarios/{identificacion}/{clave}")
	@ResponseStatus(HttpStatus.OK) 
	public ResponseEntity<?> loginUsuario(@PathVariable String identificacion,@PathVariable String clave)
	{
		Usuario usuario = null;
		Map<String, Object> response  = new HashMap<>();
		try {
			usuario = usuarioServicio.findByClaveAndIdentificacion(identificacion,clave); 
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrio un error en la bd");
			response.put("mensaje", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(usuario == null)
		{
			response.put("mensaje", "El usuario con la identificacion : ".concat(identificacion).concat(" No existe"));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario,HttpStatus.OK); 
	}

	

}
