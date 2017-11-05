"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
require("rxjs/add/operator/switchMap");
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var common_1 = require("@angular/common");
var staff_service_1 = require("../services/staff.service");
var route_params_service_1 = require("../services/route-params.service");
var PersonDetailComponent = (function () {
    function PersonDetailComponent(staffService, router, routeParamsService, route, location) {
        this.staffService = staffService;
        this.router = router;
        this.routeParamsService = routeParamsService;
        this.route = route;
        this.location = location;
    }
    PersonDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.paramMap
            .switchMap(function (params) {
            return _this.staffService
                .getPerson(+params.get('staffId'));
        }).subscribe(function (person) { return _this.person = person; });
    };
    PersonDetailComponent.prototype.edit = function () {
        this.routeParamsService.person = this.person;
        this.router.navigate(['/staff/new']);
    };
    PersonDetailComponent.prototype.goBack = function () {
        this.location.back();
    };
    return PersonDetailComponent;
}());
PersonDetailComponent = __decorate([
    core_1.Component({
        selector: 'person-detail',
        templateUrl: './staff-detail.component.html',
        styleUrls: ['./staff-detail.component.css']
    }),
    __metadata("design:paramtypes", [staff_service_1.StaffService,
        router_1.Router,
        route_params_service_1.RouteParamsService,
        router_1.ActivatedRoute,
        common_1.Location])
], PersonDetailComponent);
exports.PersonDetailComponent = PersonDetailComponent;
//# sourceMappingURL=staff-detail.component.js.map