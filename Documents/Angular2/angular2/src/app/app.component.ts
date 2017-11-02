import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
  <app-two-way-binding></app-two-way-binding>
  <h1>okkkkk</h1>
  <my-other></my-other>
  <my2-another>
  <div>
  <h1>Hello</h1>
  <p>world</p>
  </div>
  </my2-another>
  <app-databinding></app-databinding>
  <app-eventbinding></app-eventbinding>
  <app-service></app-service>
  <app-services-two></app-services-two>
 <br><br><br><br><br><br><br><br><br><br><br>
  `,
  styles: [`
  h1{
    color: red;
  }
  `]
})
export class AppComponent {
  title = 'RS Metrics';
}
