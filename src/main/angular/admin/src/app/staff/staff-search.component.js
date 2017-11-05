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
var Observable_1 = require("rxjs/Observable");
var Subject_1 = require("rxjs/Subject");
require("rxjs/add/observable/of");
require("rxjs/add/operator/catch");
require("rxjs/add/operator/debounceTime");
require("rxjs/add/operator/distinctUntilChanged");
var staff_search_service_1 = require("../services/staff-search.service");
var StaffSearchComponent = (function () {
    function StaffSearchComponent(staffSearchService, router) {
        this.staffSearchService = staffSearchService;
        this.router = router;
        this.searchTerms = new Subject_1.Subject();
    }
    StaffSearchComponent.prototype.search = function (term) {
        this.searchTerms.next(term);
    };
    StaffSearchComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.staff = this.searchTerms
            .debounceTime(300)
            .distinctUntilChanged()
            .switchMap(function (term) { return term
            ? _this.staffSearchService.search(term)
            : Observable_1.Observable.of([]); })
            .catch(function (error) {
            console.log(error);
            return Observable_1.Observable.of([]);
        });
    };
    StaffSearchComponent.prototype.gotoDetail = function (person) {
        var link = ['/detail', person.staffId];
        this.router.navigate(link);
    };
    return StaffSearchComponent;
}());
StaffSearchComponent = __decorate([
    core_1.Component({
        selector: 'person-search',
        templateUrl: './staff-search.component.html',
        styleUrls: ['./staff-search.component.css'],
        providers: [staff_search_service_1.StaffSearchService]
    }),
    __metadata("design:paramtypes", [staff_search_service_1.StaffSearchService,
        router_1.Router])
], StaffSearchComponent);
exports.StaffSearchComponent = StaffSearchComponent;
//# sourceMappingURL=staff-search.component.js.map