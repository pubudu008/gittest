import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'my-other',
  templateUrl: './other.component.html',
  styleUrls: ['./other.css']
})
export class OtherComponent{

  private items=[1,2,3,4,5];
private switch=true;
  onSwitch(){
this.switch=!this.switch;

  }

}
