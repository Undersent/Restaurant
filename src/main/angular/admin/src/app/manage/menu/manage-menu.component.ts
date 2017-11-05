import { Component } from '@angular/core';
import { Router }            from '@angular/router';

@Component({
    selector: 'manage-menu',
    templateUrl: './manage-menu.component.html',
    styleUrls: [ './manage-menu.component.css' ]
})
export class ManageMenuComponent {

    constructor(private router: Router) { }

}
