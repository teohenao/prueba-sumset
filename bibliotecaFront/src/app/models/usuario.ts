
import { Prestamo } from './prestamo';

export class Usuario{
    id:number;
    identificacion:string;
    nombres:string;
    apellidos:string;
    clave:string;
    sector:string;
    fechaNacimiento:Date;
    prestamos:Prestamo[]=[];
 }