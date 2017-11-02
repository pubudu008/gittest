import { Component } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { states, Address } from './data-model';
@Component({
  selector: 'hero-details',
  templateUrl: './hero-details.component.html',
  styleUrls: ['./hero-details.component.css']
})
export class HeroDetailsComponent {

  // name = new FormControl();

  // heroForm = new FormGroup({
  //   name: new FormControl()

  // });
  heroForm: FormGroup;
  states = states;

  constructor(private fb: FormBuilder) {
    this.createForm();
  }

  createForm() {
    this.heroForm = this.fb.group({
       name: ['', Validators.required ],
       address: this.fb.group(new Address()),
       power: '',
       sidekick: ''
  });
      //});
      
    }

    
  }
// export class HeroDetailsComponent2 {

//   heroForm = new FormGroup({
//     name: new FormControl()

//   });
// }
// export class HeroDetailComponent3 {
//   heroForm: FormGroup;

//   constructor(private fb: FormBuilder) {
//     this.createForm();
//   }

//   createForm() {
//     // this.heroForm = this.fb.group({
//     // name: '', 
//     this.heroForm = this.fb.group({
//       name: ['', Validators.required],
//     });
//     //});
//   }
// }
