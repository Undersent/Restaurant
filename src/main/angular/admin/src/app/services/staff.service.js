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
var http_1 = require("@angular/http");
require("rxjs/add/operator/toPromise");
var StaffService = (function () {
    function StaffService(http) {
        this.http = http;
        this.updateHeaders = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.username = '123';
        this.password = 'admin';
        this.staffUrl = 'http://localhost:8080/get/staff';
        this.updateStaffUrl = 'http://localhost:8080/admin/update/staff';
        this.createWaiterUrl = 'http://localhost:8080/admin/add/staff/waiter';
        this.createCookUrl = 'http://localhost:8080/admin/add/staff/cook';
        this.updateHeaders.append("Authorization", "Basic "
            + btoa(this.username + ":" + this.password));
    }
    StaffService.prototype.getWholeStaff = function () {
        var url = this.staffUrl + '/all';
        return this.http.get(url, { headers: this.updateHeaders })
            .toPromise()
            .then(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    StaffService.prototype.getPerson = function (id) {
        var url = this.staffUrl + "/id?id=" + id;
        return this.http.get(url, { headers: this.updateHeaders })
            .toPromise()
            .then(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    StaffService.prototype.create = function (person, role) {
        var url = this.getUrlFromRole(role);
        return this.http
            .post(url, JSON.stringify(person), { headers: this.updateHeaders })
            .toPromise()
            .then(function (response) {
            return 'OK';
        })
            .catch(this.handleErrorFromCreate);
    };
    StaffService.prototype.update = function (person) {
        return this.http
            .post(this.updateStaffUrl, JSON.stringify(person), { headers: this.updateHeaders })
            .toPromise()
            .then(function (response) {
            return 'OK';
        })
            .catch(this.handleError);
    };
    StaffService.prototype.handleErrorFromCreate = function (error) {
        if (error.json().message == 'staff with that pesel exists') {
            return new Promise(function (resolve, reject) { resolve("staff with that pesel exists"); });
        }
        else {
            return Promise.reject(error.message || error);
        }
    };
    StaffService.prototype.handleError = function (error) {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    };
    StaffService.prototype.getUrlFromRole = function (role) {
        if (role == 'Waiter') {
            return this.createWaiterUrl;
        }
        else {
            return this.createCookUrl;
        }
    };
    return StaffService;
}());
StaffService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], StaffService);
exports.StaffService = StaffService;
//# sourceMappingURL=staff.service.js.map