import { Component } from '@angular/core';
import { LogServiceService } from "./log-service.service";
import { DataService } from "./data.service";

@Component({
  selector: 'app-service',
  template: `
  <div>
  <input type="text" #input>
  <button (click)="onLog(input.value)" class="btn btn-success">log</button>
  <button (click)="onStore(input.value)" class="btn btn-default">store</button>
  <button (click)="onSend(input.value)" class="btn btn-primary">send</button>
  <button (click)="onGet()" class="btn btn-danger">reFresh</button>
  <p>{{value}}</p>
  </div>`,
})
export class ServiceComponent {
  value='';
items:String []=[];
  constructor(private logService:LogServiceService,private dataService:DataService) {
  }
  onLog(value:string){
  this.logService.writeToLog(value);
  }
onStore(value:string){
this.dataService.addData(value);

}

onGet(){
this.items =this.dataService.getData().slice(0);
this.logService.writeToLog('refreshed'+this.items);

}

onSend(value:string){
  this.dataService.pushData(value);
  
  }
}
