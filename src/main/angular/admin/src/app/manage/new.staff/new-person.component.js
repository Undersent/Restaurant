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
var core_1 = require("@angular/core");
var staff_1 = require("../../model/staff");
var staff_service_1 = require("../../services/staff.service");
var route_params_service_1 = require("../../services/route-params.service");
var NewPersonComponent = (function () {
    function NewPersonComponent(staffService, routeParamsService) {
        this.staffService = staffService;
        this.routeParamsService = routeParamsService;
        this.roles = ['Waiter', 'Cook'];
    }
    NewPersonComponent.prototype.ngOnInit = function () {
        if (this.routeParamsService.person) {
            this.person = this.routeParamsService.person;
            this.routeParamsService.person = null;
        }
        else {
            this.person = new staff_1.Staff();
        }
    };
    NewPersonComponent.prototype.add = function (name, surname, role, pesel) {
        if (!name || !surname || !role || !pesel) {
            return;
        }
        this.staffService.create(name, surname, role, pesel);
    };
    return NewPersonComponent;
}());
NewPersonComponent = __decorate([
    core_1.Component({
        selector: 'new-person',
        templateUrl: './new-person.component.html',
        styleUrls: ['./new-person.component.css']
    }),
    __metadata("design:paramtypes", [staff_service_1.StaffService,
        route_params_service_1.RouteParamsService])
], NewPersonComponent);
exports.NewPersonComponent = NewPersonComponent;
//# sourceMappingURL=new-person.component.js.map