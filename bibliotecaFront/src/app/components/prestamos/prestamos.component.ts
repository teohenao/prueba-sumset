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

  prestamos: Prestamo[] = [];

  constructor(private service: BibliotecaServices) {}

  ngOnInit() {
    this.service.listarPrestamos().subscribe(res => {
      res.forEach(prestamo => {
        this.prestamos.push(prestamo)
      })
    })
  }

  devolverLibro(prestamo: Prestamo) {
    this.service.devolverLibro(prestamo).subscribe(res=>{
      this.prestamos=[]
      Swal.fire('libro devuelto con exito','','success')
      this.ngOnInit();
    })
  }

  diasEntre(date1: any): number {
    date1 = new Date(date1)
    let UN_DIA = 1000 * 60 * 60 * 24;
    var hoy = new Date();
    var date1_ms = date1.getTime();
    var date2_ms = hoy.getTime();
    var difference_ms = Math.abs(date1_ms - date2_ms);
    return Math.round(difference_ms / UN_DIA);
  }

}
