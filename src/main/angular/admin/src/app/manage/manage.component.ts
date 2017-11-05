import { Component } from '@angular/core';
import { Router }            from '@angular/router';

@Component({
    selector: 'manage',
    templateUrl: './manage.component.html',
    styleUrls: [ './manage.component.css' ]
})
export class ManageComponent {

    constructor(private router: Router) { }

}
