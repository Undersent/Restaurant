import 'rxjs/add/operator/switchMap';
import { Component, OnInit }        from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import { Location }                 from '@angular/common';

import { Staff }        from '../model/staff';
import { StaffService } from '../services/staff.service';
import {RouteParamsService} from "../services/route-params.service";

@Component({
  selector: 'person-detail',
  templateUrl: './staff-detail.component.html',
  styleUrls: [ './staff-detail.component.css' ]
})
export class PersonDetailComponent implements OnInit {

  person: Staff;

    constructor(
        private staffService: StaffService,
        private router: Router,
        private routeParamsService: RouteParamsService,
        private route: ActivatedRoute,
        private location: Location
    ) {}

  ngOnInit(): void {
    this.route.paramMap
      .switchMap((params: ParamMap) => {
        return this.staffService
            .getPerson(+params.get('staffId'));
        }).subscribe(person => this.person = person);
  }

  edit(): void {
      this.routeParamsService.person = this.person;
      this.router.navigate(['/staff/new']);
  }

  goBack(): void {
    this.location.back();
  }
}
