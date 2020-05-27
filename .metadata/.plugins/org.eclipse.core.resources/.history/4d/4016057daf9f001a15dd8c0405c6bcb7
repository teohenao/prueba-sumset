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
import org.springframework.web.bind.annotation.RestController;

import com.sumset.biblioteca.modelos.entidades.Libro;
import com.sumset.biblioteca.modelos.servicios.ILibroServices;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/biblioteca")
public class LibroRESTController {
	
	@Autowired
	private ILibroServices libroServicio;
	
	@PostMapping("/libros")
	public ResponseEntity<?> crearLibro(@Valid @RequestBody Libro libro,BindingResult result)
	{
		Libro nuevoLibro = null;
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
			nuevoLibro = libroServicio.save(libro);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrio un error en la bd");
			response.put("mensaje", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "publicacion creado con exito");
		response.put("recomendacion", nuevoLibro);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/libros/todos")
	public List<Libro> libros(){
		return libroServicio.findAllLibros();
	}

}
