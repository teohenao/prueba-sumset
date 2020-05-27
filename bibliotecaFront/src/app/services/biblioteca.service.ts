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
export class BibliotecaServices {

  private _usuario: any;

  urlEndPoint:string ="http://localhost:8080/biblioteca/"

  constructor(private http:HttpClient) {}

  public get usuario():Usuario
  {
    if(this._usuario != null)
    {
      return this._usuario;
    }else if(sessionStorage.getItem('usuario')!=null)
    {
      this._usuario = JSON.parse(sessionStorage.getItem('usuario')) as Usuario;
      return this._usuario;
    }
    return null;
  }

  listarLibros():Observable<Libro[]>{
     return this.http.get<Libro[]>(this.urlEndPoint+'libros/todos');
  }

  crearPrestamo(prestamo:Prestamo):Observable<Prestamo>{
    return this.http.post<Prestamo>(this.urlEndPoint+'prestamos',prestamo)
  }

  listarPrestamos():Observable<Prestamo[]>{
    return this.http.get<Prestamo[]>(this.urlEndPoint+'prestamos/todos');
 }

 devolverLibro(prestamo:Prestamo):Observable<Prestamo>{
   return this.http.put<Prestamo>(this.urlEndPoint+'prestamos/'+prestamo.id,prestamo);
 }

 registrarLibro(libro:Libro):Observable<Libro>{
   return this.http.post<Libro>(this.urlEndPoint+'libros',libro);
 }

 loginUsuario(identificacion:string,clave:string):Observable<Usuario>{
   return this.http.get<Usuario>(this.urlEndPoint+'/usuarios/'+identificacion+'/'+clave)
 }

 cerrarSesion(){
   sessionStorage.clear()
   this._usuario=null
 }

 cantidadLibrosPrestados():Observable<number>{
  return this.http.get<number>(this.urlEndPoint+'/prestamos/cantidad')
 }

 cantidadLibrosPrestadosSector(sector:String):Observable<number>{
  return this.http.get<number>(this.urlEndPoint+'/prestamos/cantidad/'+sector)
 }





}
