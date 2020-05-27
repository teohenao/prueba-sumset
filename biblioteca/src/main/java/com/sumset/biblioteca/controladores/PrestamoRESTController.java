package com.sumset.biblioteca.controladores;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sumset.biblioteca.modelos.entidades.Libro;
import com.sumset.biblioteca.modelos.entidades.Prestamo;
import com.sumset.biblioteca.modelos.entidades.Usuario;
import com.sumset.biblioteca.modelos.entidades.rangoEdad;
import com.sumset.biblioteca.modelos.servicios.ILibroServices;
import com.sumset.biblioteca.modelos.servicios.IPrestamoServices;
import com.sumset.biblioteca.modelos.servicios.IUsuarioServices;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/biblioteca")
public class PrestamoRESTController {
	
	@Autowired
	private IPrestamoServices prestamoServicio;
	
	@Autowired
	private IUsuarioServices usuarioServicio;
	
	@Autowired
	private ILibroServices libroServicio;
	
	@PostMapping("/prestamos")
	public ResponseEntity<?> crearPrestamo(@RequestBody Prestamo prestamo,BindingResult result)
	{
		Prestamo nuevoPrestamo = null;
		Libro libro = libroServicio.findById(prestamo.getLibros().get(0).getId());
		Usuario u = usuarioServicio.findById(prestamo.getUsuario().getId());
		
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
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String fechaTexto = formatter.format(u.getFechaNacimiento());
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaNac = LocalDate.parse(fechaTexto, fmt);
			LocalDate ahora = LocalDate.now();
			Period periodo = Period.between(fechaNac, ahora);
			
		    rangoEdad rEdad = libro.getEdadNecesaria();
		    rangoEdad edad; 
		    if(periodo.getYears()<10) {
		       edad = rangoEdad.ninio;
		    }else if(periodo.getYears()>=10 && periodo.getYears()<25) {
		       edad = rangoEdad.adolescente;
		    }else if(periodo.getYears()>=25&& periodo.getYears()<50) {
		       edad = rangoEdad.adulto;
		    }else {
		    	edad=rangoEdad.adultoMayor;
		    }
		    System.out.println(rEdad);
		    System.out.println(edad);
            if(rEdad == edad){
               prestamo.setSector(u.getSector());
               prestamo.setEstado(true);
    		   nuevoPrestamo = prestamoServicio.save(prestamo);
    		   libro.setPrestamo(nuevoPrestamo);
    		   libro.setDisponibilidad(false);
    		   libro = libroServicio.save(libro);
		    }else { 
		    	response.put("mensaje", "No tiene la edad adecuada para solicitar este libro");
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		    }  
		   
		      
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrio un error en la bd");
			response.put("mensaje", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "publicacion creado con exito");
		response.put("prestamo", nuevoPrestamo);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/prestamos/{id}")
	public ResponseEntity<?> devolverPrestamo(@Valid @RequestBody Prestamo prestamo,BindingResult result, @PathVariable Long id)
	{
		Prestamo prestamoActual = prestamoServicio.findById(id);
		Prestamo prestamoActualizado = null;		
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
		if(prestamoActual == null)
		{
			response.put("mensaje", "El prestamo con el id :".concat(id.toString()).concat(" No existe"));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			prestamoActual.setEstado(false);
			prestamo.getLibros().get(0).setDisponibilidad(true);
			prestamo.getLibros().get(0).setPrestamo(null);
			libroServicio.save(prestamo.getLibros().get(0));
			prestamoActualizado = prestamoServicio.save(prestamoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrio un error en la bd");
			response.put("mensaje", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	  
		response.put("mensaje", "prestamo actualizado con exito");
		response.put("prestamo", prestamoActualizado);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED) ;
	}
	 
	 
	@GetMapping("/prestamos/todos")
	public List<Prestamo> prestamos(){
		return prestamoServicio.findAllPrestamos();
	}
	
	
	@GetMapping("/prestamos/cantidad")
	@ResponseStatus(HttpStatus.OK) 
	public int cantidadLibrosPrestados()
	{
		return prestamoServicio.cantidadLibrosPrestados();
	}
	
	
	
	@GetMapping("/prestamos/cantidad/{sector}")
	@ResponseStatus(HttpStatus.OK) 
	public int cantidadLibrosPrestadosSector( @PathVariable String sector)
	{
		return prestamoServicio.cantidadLibrosPrestadosSector(sector);
	}


}
