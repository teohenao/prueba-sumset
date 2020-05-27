import { Component, OnInit } from '@angular/core';
import { BibliotecaServices } from 'src/app/services/biblioteca.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(public service:BibliotecaServices) { }

  ngOnInit() {
  }

}
