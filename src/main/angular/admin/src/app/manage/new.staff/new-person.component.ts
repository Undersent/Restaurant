import {Component, OnInit} from '@angular/core';
import {Staff} from '../../model/staff';
import {StaffService} from '../../services/staff.service';
import {RouteParamsService} from '../../services/route-params.service';

@Component({
    selector: 'new-person',
    templateUrl: './new-person.component.html',
    styleUrls: [ './new-person.component.css' ]
})
export class NewPersonComponent implements OnInit {
    roles = ['Waiter', 'Cook'];
    private person: Staff;

    constructor(
        private staffService: StaffService,
        private routeParamsService: RouteParamsService
    ) {}

    ngOnInit(): void {
        if (this.routeParamsService.person) {
            this.person = this.routeParamsService.person;
            this.routeParamsService.person = null;
        } else {
            this.person = new Staff();
        }
    }

    add(name: string, surname: string, role: string, pesel?: string): void {
        if (!name || !surname || !role) { return; }
        this.staffService.create(name, surname, role, pesel);
    }
}
