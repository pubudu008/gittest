import { DataSource } from "@angular/cdk/collections";
import { CollectionViewer } from "@angular/cdk/collections";
import { Observable } from "rxjs/Observable";
import { AppService } from "../app.service";

export class CourseDatasource extends DataSource<any>{

    constructor(private service: AppService) {
        super();


    }
    connect(collectionViewer: CollectionViewer): Observable<any[]> {
        return this.service.getCourses().map(json => json._embedded.courses);
    }
    disconnect(collectionViewer: CollectionViewer): void {
        throw new Error("Method not implemented.");
    }


}