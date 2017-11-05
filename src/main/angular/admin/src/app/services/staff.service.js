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
        this.staffUrl = 'http://localhost:8080/get/staff';
    }
    StaffService.prototype.getWholeStaff = function () {
        var url = this.staffUrl + '/all';
        return this.http.get(url)
            .toPromise()
            .then(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    StaffService.prototype.getPerson = function (id) {
        var url = this.staffUrl + "/id?id=" + id;
        return this.http.get(url)
            .toPromise()
            .then(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    StaffService.prototype.delete = function (id) {
        var url = this.staffUrl + "/" + id;
        return this.http.delete(url, { headers: this.updateHeaders })
            .toPromise()
            .then(function () { return null; })
            .catch(this.handleError);
    };
    StaffService.prototype.create = function (name, surname, role, pesel) {
        return this.http
            .post(this.staffUrl, JSON.stringify({ name: name }), { headers: this.updateHeaders })
            .toPromise()
            .then(function (res) { return res.json().data; })
            .catch(this.handleError);
    };
    StaffService.prototype.update = function (person) {
        var url = this.staffUrl + "/" + person.staffId;
        return this.http
            .put(url, JSON.stringify(person), { headers: this.updateHeaders })
            .toPromise()
            .then(function () { return person; })
            .catch(this.handleError);
    };
    StaffService.prototype.handleError = function (error) {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    };
    return StaffService;
}());
StaffService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], StaffService);
exports.StaffService = StaffService;
//# sourceMappingURL=staff.service.js.map