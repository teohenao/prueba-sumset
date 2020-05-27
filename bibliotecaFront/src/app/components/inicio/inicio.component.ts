import { Component, OnInit } from '@angular/core';
import { BibliotecaServices } from 'src/app/services/biblioteca.service';
import { Libro } from 'src/app/models/libro';
import { Usuario } from 'src/app/models/usuario';
import { Prestamo } from 'src/app/models/prestamo';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss']
})
export class InicioComponent implements OnInit {

  libros:Libro[]=[];

  constructor(private service:BibliotecaServices,private route:Router) { }

  ngOnInit() {
    this.service.listarLibros().subscribe(res=>{
      res.forEach(libro => {
        this.libros.push(libro);
      });
      console.log(this.libros)
    })
  }

  solicitarLibro(l:Libro){
    let p = new Prestamo();
    p.libros.push(l);
    if(!this.service.usuario){
      this.route.navigate(['/inicio-sesion'])
      Swal.fire('pro favor inicie sesion para poder solicitar un prestamo','','error');
      return
    }
    p.usuario = this.service.usuario
    this.service.crearPrestamo(p).subscribe(res=>{
      console.log(res)
      Swal.fire('prestamo creado con exito','','success')
      this.route.navigate(['/prestamos'])
    },e=>{
      Swal.fire('error al solicitar prestamo',e.error.mensaje,'error');
    })
  }



}
