import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { StaffComponent }      from './staff/staff.component';
import { PersonDetailComponent }  from './staff/staff-detail.component';
import { StaffService }          from './services/staff.service';
import { NewPersonComponent } from './manage/new.staff/new-person.component';
import {ManageComponent} from './manage/manage.component';
import {ManageMenuComponent} from './manage/menu/manage-menu.component';
import {RouteParamsService} from "./services/route-params.service";
import {MenuService} from "./services/menu.service";
import {EditMenuComponent} from "./manage/menu/edit-menu.component";

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    PersonDetailComponent,
    StaffComponent,
    NewPersonComponent,
    ManageComponent,
    ManageMenuComponent,
    EditMenuComponent
  ],
  providers: [
      StaffService,
      RouteParamsService,
      MenuService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
