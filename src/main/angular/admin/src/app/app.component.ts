import { Component }          from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
    <h1>{{title}}</h1>
    <nav>
        <a routerLink="/dashboard" routerLinkActive="active" style="margin:1%">
            Dashboard</a>
        <a routerLink="/staff" routerLinkActive="active">
            Staff</a>
        <a routerLink="/manage" routerLinkActive="active">
            Manage</a>
    </nav>
    <router-outlet></router-outlet>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Ekel management system';
}
