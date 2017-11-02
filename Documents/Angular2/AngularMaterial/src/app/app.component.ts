import { Component } from '@angular/core';
import {FormControl} from '@angular/forms';
import {FormsModule} from '@angular/forms';
import { DateAdapter, NativeDateAdapter } from '@angular/material';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
 // myControl: FormControl = new FormControl();
  
    options = [
      'One',
      'Two',
      'Three'
     ];
     constructor(dateAdapter: DateAdapter<NativeDateAdapter>) {
      dateAdapter.setLocale('de-DE');
    }
}
