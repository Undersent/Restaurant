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

    add(name: string, surname: string, role: string, pesel: string): void {
        if (!name || !surname || !role || !pesel) {
            console.log("Some fields are not present"); return; }
        let person: Staff = new Staff();
        person.firstName = name;
        person.lastName = surname;
        person.pesel = pesel;
        person.roles = this.getRole(role);
        let serviceResponse = this.staffService.create(person, role);
        serviceResponse.then((res: string) => {
            if(res == "staff with that pesel exists"){
                this.staffService.update(person);
            }
        });
    }

    private getRole(role: string): Set<string> {
        let roles = new Set();
        if (role == 'Waiter'){
            roles.add({id:1, role:"ROLE_STAFF"});
        }
        if (role == 'Cook'){
            roles.add({id:2, role:"ROLE_COOK"});
        }
        return roles;
    }
}
