import { Component, OnInit } from '@angular/core';
import { Libro } from 'src/app/models/libro';
import Swal from 'sweetalert2';
import { Prestamo } from 'src/app/models/prestamo';
import { BibliotecaServices } from 'src/app/services/biblioteca.service';

@Component({
  selector: 'app-crear-libro',
  templateUrl: './crear-libro.component.html',
  styleUrls: ['./crear-libro.component.scss']
})
export class CrearLibroComponent implements OnInit {

  libro:Libro;
  datos;
  opcionSeleccionado: string  = '0';

  constructor(private service:BibliotecaServices) {}


  ngOnInit() {
    this.libro = new Libro();
  }

  registro(){
    let select = this.opcionSeleccionado.valueOf() as any;
    select = select as number
    this.libro.edadNecesaria = select
    this.libro.disponibilidad=true
    console.log(this.libro)
    this.service.registrarLibro(this.libro).subscribe(res=>{
      console.log(res)
    })
  }

}
