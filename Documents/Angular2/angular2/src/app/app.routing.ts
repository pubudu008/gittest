
import { Routes, RouterModule } from "@angular/router";
import { OtherComponent } from "./other/other.component";
import { DatabindingComponent } from "./databinding/databinding.component";

const APP_ROUTES: Routes = [
    {path :'user',component:DatabindingComponent},
    {path :'',component:OtherComponent}
];
export const routing=RouterModule.forRoot(APP_ROUTES);