import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-two-way-binding',
  template:  `<input type="text" [(ngModel)]="person.name" #name>
  <input type="text" [(ngModel)]="person.name">
  `


})
export class TwoWayBindingComponent {
person={
name: "pubudu",
age:23

};
  
}
