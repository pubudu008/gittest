import { DataSource } from "@angular/cdk/collections";
import { AppServiceService } from "../app-service.service";
import { Observable } from "rxjs/Observable";
import { CollectionViewer } from "@angular/cdk/collections";

export class DatabaseDatasource extends DataSource<any>{
constructor(private service:AppServiceService){
    super();

}


    connect(collectionViewer: CollectionViewer): Observable<any[]> {
       return this.service.getDataAll().map(json=>json._embedded.users);
    }
    disconnect(collectionViewer: CollectionViewer): void {
        throw new Error("Method not implemented.");
    }


}