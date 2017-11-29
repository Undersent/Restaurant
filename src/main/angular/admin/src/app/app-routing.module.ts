import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent }   from './dashboard/dashboard.component';
import { StaffComponent }      from './staff/staff.component';
import { PersonDetailComponent }  from './staff/staff-detail.component';
import {NewPersonComponent} from './manage/new.staff/new-person.component';
import {ManageComponent} from './manage/manage.component';
import {ManageMenuComponent} from './manage/menu/manage-menu.component';
import {EditMenuComponent} from "./manage/menu/edit-menu.component";

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard',  component: DashboardComponent },
  { path: 'detail/:staffId', component: PersonDetailComponent },
  { path: 'staff',     component: StaffComponent },
  { path: 'staff/new',     component: NewPersonComponent },
  { path: 'manage',     component: ManageComponent },
  { path: 'manage/menu',     component: ManageMenuComponent },
  { path: 'manage/menu/edit',     component: EditMenuComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
