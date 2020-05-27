package com.sumset.biblioteca.modelos.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entidad que representa un libro en la base de datos
 * @author Mateo Henao
 */
@Entity
@Table(name="libros")
public class Libro implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "nombre no puede estar vacio")
	@Size(min = 5,max = 20)
	@Column(name="nombre")
	private String nombre;
	
	@NotEmpty(message = "autor no puede estar vacio")
	@Size(min = 5,max = 20)
	@Column(name="autor")
	private String autor;
	
	@Column(name="disponibilidad")
	private Boolean disponibilidad;
	
	@NotEmpty(message = "descripcion no puede estar vacio")
	@Size(min = 5,max = 250)
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name = "foto")
	private String foto;
	
	/**
	 * relacion de varios libros para realizar algun prestamo
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value={"libros","hibernateLazyInitializer","handler"},allowSetters = true)
	private Prestamo prestamo;
	
	/**
	 * Rango de edad necesaria para poder solicitar el libro
	 */
	private rangoEdad edadNecesaria;
	
	
	public Libro() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Boolean getDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(Boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public rangoEdad getEdadNecesaria() {
		return edadNecesaria;
	}
	public void setEdadNecesaria(rangoEdad edadNecesaria) {
		this.edadNecesaria = edadNecesaria;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Prestamo getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	private static final long serialVersionUID = 1L;

}
