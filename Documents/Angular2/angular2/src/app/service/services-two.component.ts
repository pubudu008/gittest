import { Component, OnInit } from '@angular/core';
import { LogServiceService } from "./log-service.service";
import { DataService } from "./data.service";

@Component({
  selector: 'app-services-two',
  template: `
  <div>
  <input type="text" #input>
  <button (click)="onLog(input.value)" class="btn btn-primary">log</button>
  <button (click)="onStore(input.value)" class="btn btn-default">store</button>
  <button (click)="onGet()" class="btn btn-danger">reFresh</button>
  <p>{{value}}</p>
  </div>`,
})
export class ServicesTwoComponent implements OnInit{


  items:String []=[];
  value='';
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
    
    }

    ngOnInit(){
      this.dataService.pushedData.subscribe(

data=>this.value=data

      );
    }

  }
    