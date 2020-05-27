import { Libro } from './libro';
import { Usuario } from './usuario';

export class Prestamo{
    id:number;
    fechaPrestamo:Date;
    usuario:Usuario;
    estado:boolean;
    libros:Libro[]=[];
 }
 