import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { InicioComponent } from './components/inicio/inicio.component';
import { PrestamosComponent } from './components/prestamos/prestamos.component';
import { NavbarComponent } from './components/templates/navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { CrearLibroComponent } from './components/crear-libro/crear-libro.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    InicioComponent,
    PrestamosComponent,
    NavbarComponent,
    CrearLibroComponent
  ],
  imports: [
    FormsModule, 
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
