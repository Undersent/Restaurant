"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var platform_browser_1 = require("@angular/platform-browser");
var forms_1 = require("@angular/forms");
var http_1 = require("@angular/http");
var app_routing_module_1 = require("./app-routing.module");
var app_component_1 = require("./app.component");
var dashboard_component_1 = require("./dashboard/dashboard.component");
var staff_component_1 = require("./staff/staff.component");
var staff_detail_component_1 = require("./staff/staff-detail.component");
var staff_service_1 = require("./services/staff.service");
var staff_search_component_1 = require("./staff/staff-search.component");
var new_person_component_1 = require("./manage/new.staff/new-person.component");
var manage_component_1 = require("./manage/manage.component");
var manage_menu_component_1 = require("./manage/menu/manage-menu.component");
var route_params_service_1 = require("./services/route-params.service");
var staff_search_service_1 = require("./services/staff-search.service");
var menu_service_1 = require("./services/menu.service");
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    core_1.NgModule({
        imports: [
            platform_browser_1.BrowserModule,
            forms_1.FormsModule,
            http_1.HttpModule,
            app_routing_module_1.AppRoutingModule
        ],
        declarations: [
            app_component_1.AppComponent,
            dashboard_component_1.DashboardComponent,
            staff_detail_component_1.PersonDetailComponent,
            staff_component_1.StaffComponent,
            staff_search_component_1.StaffSearchComponent,
            new_person_component_1.NewPersonComponent,
            manage_component_1.ManageComponent,
            manage_menu_component_1.ManageMenuComponent,
        ],
        providers: [
            staff_service_1.StaffService,
            staff_search_service_1.StaffSearchService,
            route_params_service_1.RouteParamsService,
            menu_service_1.MenuService
        ],
        bootstrap: [app_component_1.AppComponent]
    })
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map