import {
  Component,
  OnInit
} from '@angular/core';
import {
  Prestamo
} from 'src/app/models/prestamo';
import {
  BibliotecaServices
} from 'src/app/services/biblioteca.service';
import {
  THIS_EXPR
} from '@angular/compiler/src/output/output_ast';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-prestamos',
  templateUrl: './prestamos.component.html',
  styleUrls: ['./prestamos.component.scss']
})
export class PrestamosComponent implements OnInit {

  //prestamos activos en la base de datos
  prestamos: Prestamo[] = [];
  //sector por el cual realizar la busqueda de cantidad
  sector:string;
  //cantidad total de prestamos en un sector
  totalSector:number=0;
  //total de prestamos en el sistema
  totalPrestamos:number=0

  constructor(private service: BibliotecaServices) {}

  ngOnInit() {
    //consumimos el servicio que lista todos los prestamos
    this.service.listarPrestamos().subscribe(res => {
      res.forEach(prestamo => {
        this.prestamos.push(prestamo)
      })
    })
    //consumimos el servicio que obtiene la cantidad de prestamos del sistema
    this.service.cantidadLibrosPrestados().subscribe(res=>{
      this.totalPrestamos = res
    })
  }

  //metodo que permite realizar la devolucion de un libro
  devolverLibro(prestamo: Prestamo) {
    this.service.devolverLibro(prestamo).subscribe(res=>{
      this.prestamos=[]
      Swal.fire('libro devuelto con exito','','success')
      this.ngOnInit();
    })
  }

  //metodo que permite obteber la cantidad de dias que lleva un libro prestado
  diasEntre(date1: any): number {
    date1 = new Date(date1)
    let UN_DIA = 1000 * 60 * 60 * 24;
    var hoy = new Date();
    var date1_ms = date1.getTime();
    var date2_ms = hoy.getTime();
    var difference_ms = Math.abs(date1_ms - date2_ms);
    return Math.round(difference_ms / UN_DIA);
  }

  //metodo que permite obtener la cantidad de prestamos de determinado sector
  cantidadSector(){
    this.service.cantidadLibrosPrestadosSector(this.sector).subscribe(res=>{
      this.totalSector = res
      console.log(res)
    })
  }

}
