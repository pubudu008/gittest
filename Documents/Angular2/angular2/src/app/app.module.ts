import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { OtherComponent } from './other/other.component';
import { AnotherComponent } from './another.component';
import { DatabindingComponent } from './databinding/databinding.component';
import { EventbindingComponent } from './eventbinding.component';
import { HeighlightDirective } from './heighlight.directive';
import { ServiceComponent } from './service/service.component';
import { LogServiceService } from './service/log-service.service';
import { ServicesTwoComponent } from './service/services-two.component';
import { DataService } from "./service/data.service";
import { TwoWayBindingComponent } from './other/two-way-binding.component';
import { FormsModule } from '@angular/forms';
import { routing } from './app.routing';


@NgModule({
  declarations: [
    AppComponent,
    OtherComponent,
    AnotherComponent,
    DatabindingComponent,
    EventbindingComponent,
    HeighlightDirective,
    ServiceComponent,
    ServicesTwoComponent,
    TwoWayBindingComponent
  ],
  imports: [
    BrowserModule,FormsModule,routing
  ],
  providers: [LogServiceService,DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
