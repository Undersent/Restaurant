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
import { StaffSearchComponent }  from './staff/staff-search.component';
import { NewPersonComponent } from './manage/new.staff/new-person.component';
import {ManageComponent} from './manage/manage.component';
import {ManageMenuComponent} from './manage/menu/manage-menu.component';
import {RouteParamsService} from "./services/route-params.service";
import {StaffSearchService} from "./services/staff-search.service";
import {MenuService} from "./services/MenuService";

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
    StaffSearchComponent,
    NewPersonComponent,
    ManageComponent,
    ManageMenuComponent,
  ],
  providers: [
      StaffService,
      StaffSearchService,
      RouteParamsService,
      MenuService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
