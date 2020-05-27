import { Libro } from './libro';
import { Usuario } from './usuario';
//modelo que representa los prestamos en el front
export class Prestamo{
    id:number;
    fechaPrestamo:Date;
    usuario:Usuario;
    estado:boolean;
    libros:Libro[]=[];
 }
 