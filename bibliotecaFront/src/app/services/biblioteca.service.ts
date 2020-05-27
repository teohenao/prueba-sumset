import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Libro } from '../models/libro';
import { Prestamo } from '../models/prestamo';
import {map,catchError,tap} from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Usuario } from '../models/usuario';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
/**
 * Servicios de la biblioteca desde el api del backend
 */
export class BibliotecaServices {
   
  //usuario loggeado
  private _usuario: any;

  //endpoint del backend
  urlEndPoint:string ="http://localhost:8080/biblioteca/"

  constructor(private http:HttpClient,private route:Router) {}

  //metodo que permite obtener el usuario loggeado
  public get usuario():Usuario
  {
    if(this._usuario != null){return this._usuario;
    }else if(sessionStorage.getItem('usuario')!=null){
      this._usuario = JSON.parse(sessionStorage.getItem('usuario')) as Usuario;
      return this._usuario;
    } return null;
  }

  //metodo que consule el servicio que lista los libros de la base de datos desde el backend
  listarLibros():Observable<Libro[]>{
     return this.http.get<Libro[]>(this.urlEndPoint+'libros/todos');
  }
  //metodo que consume el servicio que realiza el registro de un prestamo en la base de datos del backend
  crearPrestamo(prestamo:Prestamo):Observable<Prestamo>{
    return this.http.post<Prestamo>(this.urlEndPoint+'prestamos',prestamo)
  }
  //metodo que permite listar todos los prestamos desde el backend
  listarPrestamos():Observable<Prestamo[]>{
    return this.http.get<Prestamo[]>(this.urlEndPoint+'prestamos/todos');
 }
 //metodo que permite actualizar el estado de un libro cuando es devuelto
 devolverLibro(prestamo:Prestamo):Observable<Prestamo>{
   return this.http.put<Prestamo>(this.urlEndPoint+'prestamos/'+prestamo.id,prestamo);
 }
//metodo que consume el servicio que realiza el registro de un libro en la base de datos del backend
 registrarLibro(libro:Libro):Observable<Libro>{
   return this.http.post<Libro>(this.urlEndPoint+'libros',libro);
 }
//metodo que consume el servicio de loggeo de un usuario desde el backend
 loginUsuario(identificacion:string,clave:string):Observable<Usuario>{
   return this.http.get<Usuario>(this.urlEndPoint+'/usuarios/'+identificacion+'/'+clave)
 }
//metodo para cerrar sesion de un usuario que se encuentre loggeado
 cerrarSesion(){
   sessionStorage.clear()
   this._usuario=null
   this.route.navigate(['/inicio'])
 }
//metodo para obtener la cantidad de libros que se encuentran prestados a la fecha
 cantidadLibrosPrestados():Observable<number>{
  return this.http.get<number>(this.urlEndPoint+'/prestamos/cantidad')
 }
//metodoq ue permite obtener la cantidad de libros que se encuentran prestados a la fecha por medio de un sector
 cantidadLibrosPrestadosSector(sector:String):Observable<number>{
  return this.http.get<number>(this.urlEndPoint+'/prestamos/cantidad/'+sector)
 }
}
