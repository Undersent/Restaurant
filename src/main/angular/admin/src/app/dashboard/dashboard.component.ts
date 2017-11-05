import { Component, OnInit } from '@angular/core';

import { Staff }        from '../model/staff';
import { StaffService } from '../services/staff.service';

@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  staff: Staff[] = [];

  constructor(private staffService: StaffService) { }

  ngOnInit(): void {
    this.staffService.getWholeStaff()
      .then(staff => this.staff = staff.slice(1, 5));
  }
}
