import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';

import { Staff }                from '../model/staff';
import { StaffService }         from '../services/staff.service';

@Component({
  selector: 'staff',
  templateUrl: './staff.component.html',
  styleUrls: [ './staff.component.css' ]
})
export class StaffComponent implements OnInit {
  staff: Staff[];

  constructor(
    private staffService: StaffService,
    private router: Router) { }

  getWholeStaff(): void {
    this.staffService
        .getWholeStaff()
        .then(staff => this.staff = staff);
  }

  ngOnInit(): void {
    this.getWholeStaff();
  }

  onSelect(person: Staff): void {
      this.router.navigate(['/detail', person.staffId]);
  }
}
