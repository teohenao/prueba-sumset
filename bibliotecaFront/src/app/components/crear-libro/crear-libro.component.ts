import { Component, OnInit } from '@angular/core';
import { Libro } from 'src/app/models/libro';
import Swal from 'sweetalert2';
import { Prestamo } from 'src/app/models/prestamo';
import { BibliotecaServices } from 'src/app/services/biblioteca.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-crear-libro',
  templateUrl: './crear-libro.component.html',
  styleUrls: ['./crear-libro.component.scss']
})
export class CrearLibroComponent implements OnInit {

  //libro a crear
  libro:Libro;
  //opcion seleccion para el select de edad necesaria para solicitar el libro
  opcionSeleccionado: string  = '0';

  constructor(private service:BibliotecaServices,private route:Router) {}
  
  ngOnInit() {
    this.libro = new Libro();
  }
  
  //metodo que realiza el registro de un libro consumiendo el servicio desde el backend
  registro(){
    let select = this.opcionSeleccionado.valueOf() as any;
    select = select as number
    this.libro.edadNecesaria = select
    this.libro.disponibilidad=true
    this.service.registrarLibro(this.libro).subscribe(res=>{
      this.route.navigate(['/inicio'])
      Swal.fire('libro creado con exito','','success')
    })
  }

}
