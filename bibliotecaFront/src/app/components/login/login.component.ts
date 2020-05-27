import { Component, OnInit } from '@angular/core';
import { BibliotecaServices } from 'src/app/services/biblioteca.service';
import { Credenciales } from 'src/app/models/credenciales';
import { Usuario } from 'src/app/models/usuario';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  //credenciales para realizar el loggeo del usuario
  credenciales:Credenciales;
  
  constructor(private service:BibliotecaServices,private route:Router) { }

  ngOnInit() {
    this.credenciales=new Credenciales();
    //validacion si el usuario ya se encuentra loggeado
    if(this.service.usuario){
      Swal.fire('debe cerrar sesion antes','','info')
      this.route.navigate(['/inicio'])
    }
  }

  //metodo que permite al usuario loggearse en la aplicacion y asi poder solicitar libros
  login(){
   this.service.loginUsuario(this.credenciales.identificacion,this.credenciales.clave).subscribe(res=>{
     if(res){
      let u = new Usuario();
      u.nombres= res.nombres
      u.apellidos = res.apellidos
      u.id = res.id
      sessionStorage.setItem('usuario',JSON.stringify(u));
      this.route.navigate(['/inicio'])
      Swal.fire('bienvenido a la biblioteca',u.nombres,'success')
     }
   },err=>{
     Swal.fire('por favor revise sus credenciales de inicio','','error')
   })
  }

}
