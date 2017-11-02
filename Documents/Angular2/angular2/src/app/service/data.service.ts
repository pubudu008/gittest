import { Injectable, EventEmitter } from '@angular/core';
import { LogServiceService } from "./log-service.service";


@Injectable()
export class DataService {

pushedData = new EventEmitter<string>();
private data :String[]=[];
  constructor(private logService:LogServiceService) { }


  addData(input:string){
this.data.push(input);
this.logService.writeToLog('saved item'+input);
  }

  getData(){

    return this.data;
  }

  pushData(value:string){
this.pushedData.emit(value);

  }
}
