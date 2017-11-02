import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MaterialModule,MdAutocompleteModule } from '@angular/material';
import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppServiceService } from "./app-service.service";
import { HttpModule } from "@angular/http";
import {FormsModule} from '@angular/forms';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    HttpModule,
    MaterialModule,
    BrowserModule,
    BrowserAnimationsModule,FormsModule,MdAutocompleteModule
  ],
  providers: [AppServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
