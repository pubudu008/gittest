import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { MdAutocompleteModule, MaterialModule, MdDatepickerModule, MdNativeDateModule } from '@angular/material';
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [MdDatepickerModule, MdNativeDateModule,ReactiveFormsModule,MaterialModule,FormsModule,
    BrowserModule,MdAutocompleteModule,BrowserAnimationsModule ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
