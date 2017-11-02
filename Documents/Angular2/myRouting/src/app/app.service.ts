import { Injectable } from '@angular/core';
import { Http,Headers } from "@angular/http";
import 'rxjs/Rx';

@Injectable()
export class AppService {

  constructor(private _http: Http) {

  }

  getApi() {
    return this._http.get('/api').map(res => {
      console.log("Resposen is ", res);
      console.log("Reponse Body", res.json());
      return res.json();
    });
  }

  getCourses() {
    return this._http.get('/api/courses').map(res => res.json());
  }

  setCourses(couse:any,url:string) {
    const body=JSON.stringify(couse);
    const headers=new Headers();
 headers.append('content-type','application/json');
    if(url==null){
   
    return this._http.post('/api/courses',body,{headers:headers}).map(data => data.json());
    }else{
//     const body=JSON.stringify(couse);
//    const headers=new Headers();
// headers.append('content-type','application/json');\
console.log(url);
    return this._http.put(url,body,{headers:headers}).map(data => data.json());

    }
  }
  deleteCourse(couse:any,url:string) {
    const body=JSON.stringify(couse);
    const headers=new Headers();
 headers.append('content-type','application/json');
  
    return this._http.delete(url,{headers:headers,body:""}).map(data => data.json());
    }
  
//   setCoursess(couse:any,self:string) {
//     const body=JSON.stringify(couse);
//    const headers=new Headers();
// headers.append('content-type','application/json');
//     return this._http.put(self,body,{headers:headers}).map(data => data.json());
//   }
}
