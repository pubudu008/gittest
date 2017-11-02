import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-another',
  template:`
  <h1>another component</h1>
  <button (click)="onNavigate()">home</button>
  `
})
export class AnotherComponent{

  constructor(private router: Router) { }
onNavigate(){

  this.router.navigate(['/']);
}

}
