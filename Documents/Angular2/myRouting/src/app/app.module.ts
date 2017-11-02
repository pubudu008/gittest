import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { OtherComponentComponent } from './other-component.component';
import { routing } from "./app.routing";
import { AnotherComponent } from './another.component';
import { AppService } from "./app.service";
import { FormsModule,FormControl } from '@angular/forms';
import { MaterialModule, MdButtonModule, MdTableModule} from '@angular/material';
import {NoopAnimationsModule,BrowserAnimationsModule} from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    OtherComponentComponent,
    AnotherComponent
  ],
  imports: [
    HttpModule,FormsModule,MaterialModule,NoopAnimationsModule,
    BrowserModule, routing,MdButtonModule,MdTableModule,BrowserAnimationsModule
  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }
