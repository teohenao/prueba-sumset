import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InicioComponent } from './components/inicio/inicio.component';
import { PrestamosComponent } from './components/prestamos/prestamos.component';
import { LoginComponent } from './components/login/login.component';
import { CrearLibroComponent } from './components/crear-libro/crear-libro.component';


//rutas del modulo principal para esta prueba
const routes: Routes = [
  {path: '',redirectTo:'/inicio',pathMatch:'full'},
  {path:'inicio',component:InicioComponent},
  {path:'prestamos',component:PrestamosComponent},
  {path:'inicio-sesion',component:LoginComponent},
  {path:'crear-libro',component:CrearLibroComponent},
  { path: '**', component:InicioComponent } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
