import { Injectable } from '@angular/core';
import {Http,Headers} from '@angular/http';
import 'rxjs/Rx';
@Injectable()
export class AppServiceService {

  constructor(private _http:Http) {


   }
   getApi(){

    return this._http.get('/api').map(res=>{
      console.log("Reponse Body", res.json());
      
      return res.json();
    
    }
    
    );
   }
saveData(company:any){
const body=JSON.stringify(company);
const headers=new Headers();
headers.append('content-type','application/json')
return this._http.post('/api/companies',body,{headers:headers}).map(data=>data.json());

}

saveUser(user:any){

  const body=JSON.stringify(user);
  const headers=new Headers();
  headers.append('content-type','application/json')
  return this._http.post('/api/users',body,{headers:headers}).map(data=>data.json());
  
}

getDataCompany(){
return this._http.get("/api/companies").map(res=>res.json());

}

getDataAll(){
  return this._http.get("/api/users?projection=company-name").map(res=>res.json());
  
  }

  saveBuilding(building:any){
const body=JSON.stringify(building);
const headers=new Headers();
headers.append('content-type','application/json');
return this._http.post('/api/buildings',body,{headers:headers}).map(res=>res.json());

  }
}
