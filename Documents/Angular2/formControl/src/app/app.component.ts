import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template:`<div class="container">
  <h1>Reactive Forms</h1>
  <hero-details></hero-details>
</div>`
})
export class AppComponent {
  title = 'app';
}
