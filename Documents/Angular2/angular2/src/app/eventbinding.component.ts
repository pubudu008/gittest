import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-eventbinding',
  template:`<button (click)="onClicked()">click me!</button>`
 
})

export class EventbindingComponent{
onClicked(){

  alert("it worked");
}

}
