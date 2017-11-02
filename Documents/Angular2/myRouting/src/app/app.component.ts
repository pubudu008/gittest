import { Component } from '@angular/core';
import { AppService } from "./app.service";
import { FormControl, FormsModule } from '@angular/forms';
import { CourseDatasource } from "./models/course.datasource";
import { MdSnackBar } from "@angular/material";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  courses: any[];
  course = {
    id: "",
    name: "",
    description: ""

  }

  url:string;
  
  dataSource: CourseDatasource;
  displayedColumns = ['name', 'description'];

  constructor(private _service: AppService,public snackBar: MdSnackBar) {
    _service.getApi().subscribe(json => {
      console.log("JSON is", json);
    })
    this.dataSource = new CourseDatasource(_service);
    //this.onGet();


  }
  onSave() {
    this._service.setCourses(this.course,this.url).subscribe(json => {
      data => console.log(data);

      //  this.onGet();
    });

    this.snackBar.open("Do you want to Save it..","okk", {
      duration: 2000,
    });
  

  }
  onDelete() {
    this._service.deleteCourse(this.course,this.url).subscribe(json => {
      data => console.log(data);

      //  this.onGet();
    });



  }
  onRowClick(row) {
   
    this.url = row._links.self.href;
    this.course.name = row.name;
    this.course.description = row.description;
    console.log("self define:",row);
  }
  onClear() {
    
     this.url = null,
     this.course.id = "",
     this.course.name = "",
     this.course.description = ""
     console.log(this.url);
   }

  // onUpdate(url: string, name: string, description: string) {
  //   this._service.setCoursess({ name: name, description: description }, this.course.url).subscribe(json => {
  //     data => console.log(data);

  //     //  this.onGet();
  //   });



  // }
  onGet() {

    this._service.getCourses().subscribe(json => {
      this.courses = json._embedded.courses;
    })
  }
}
