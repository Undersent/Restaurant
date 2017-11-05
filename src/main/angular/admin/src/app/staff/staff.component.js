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
var router_1 = require("@angular/router");
var staff_service_1 = require("../services/staff.service");
var StaffComponent = (function () {
    function StaffComponent(staffService, router) {
        this.staffService = staffService;
        this.router = router;
    }
    StaffComponent.prototype.getWholeStaff = function () {
        var _this = this;
        this.staffService
            .getWholeStaff()
            .then(function (staff) { return _this.staff = staff; });
    };
    StaffComponent.prototype.ngOnInit = function () {
        this.getWholeStaff();
    };
    StaffComponent.prototype.onSelect = function (person) {
        this.router.navigate(['/detail', person.staffId]);
    };
    return StaffComponent;
}());
StaffComponent = __decorate([
    core_1.Component({
        selector: 'staff',
        templateUrl: './staff.component.html',
        styleUrls: ['./staff.component.css']
    }),
    __metadata("design:paramtypes", [staff_service_1.StaffService,
        router_1.Router])
], StaffComponent);
exports.StaffComponent = StaffComponent;
//# sourceMappingURL=staff.component.js.map