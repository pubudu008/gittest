import { Routes, RouterModule } from "@angular/router";
import { AppComponent } from "./app.component";
import { OtherComponentComponent } from "./other-component.component";
import { AnotherComponent } from "./another.component";

const APP_ROUTES: Routes = [
    {path :'user',component:AnotherComponent},
    {path :'other',component:OtherComponentComponent}
];
export const routing=RouterModule.forRoot(APP_ROUTES);