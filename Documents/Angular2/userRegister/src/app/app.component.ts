import { Component } from '@angular/core';
import { AppServiceService } from "./app-service.service";
import { FormsModule } from '@angular/forms';
import { DatabaseDatasource } from "./model/database.datasource";
import { Database2Datasource } from "./model/database2.datasource";
import { MdOptionSelectionChange } from "@angular/material";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  user = {
    firstName: "",
    lastName: "",
    company: ""
  }
  company = {
   name: "",
    email: ""
  }

  building={
    name:"",
    code:"",
    company:""
  }
  buildingComp={
    comp:""
  

  }
companies:any[];

dataSource: DatabaseDatasource;
displayedColumns=['Fname','Lname','Cname','CompanyEmail'];

// dataSource2: Database2Datasource;
// displayed2Columns=['name'];
  constructor(private _service: AppServiceService) {
    _service.getApi().subscribe(json => {
      console.log("JSON IS:", json);
this.getData();
    })
this.dataSource=new DatabaseDatasource(_service);
// this.dataSource2=new Database2Datasource(_service);
  }

  onSave() {
    this._service.saveData(this.company).subscribe(json => {
      //data=>console.log(data);
      this.user.company = json._links.self.href;
      this._service.saveUser(this.user).subscribe(json => {
        console.log("json is:", json);
      });
    });

  }


  getData(){
this._service.getDataCompany().subscribe(json=>{
this.companies=json._embedded.companies;

});

  }

  // onRowClick(row){

  //   row._links.self.href;
  //  console.log(row._links.self.href);

  // }

  onSaveBuilding(){
this._service.saveBuilding(this.building).subscribe(json=>{
console.log(json);

});
  }

  callSomeFunction(event: MdOptionSelectionChange,compny:any){
    if (event.source.selected) {
      this.building.company=compny._links.self.href;
  }

    
    console.log("okkkkkkk",this.building.company);
    

  }
}

