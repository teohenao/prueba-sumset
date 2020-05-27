import { Prestamo } from './prestamo';
//modelo de usuario que representa la entidad usuario en el front
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