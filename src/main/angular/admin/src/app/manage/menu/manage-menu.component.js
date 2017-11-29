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
var menu_service_1 = require("../../services/menu.service");
var ManageMenuComponent = (function () {
    function ManageMenuComponent(router, menuService) {
        this.router = router;
        this.menuService = menuService;
    }
    ManageMenuComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.menuService.getAllMeal()
            .then(function (meals) { return _this.menu = meals; });
    };
    return ManageMenuComponent;
}());
ManageMenuComponent = __decorate([
    core_1.Component({
        selector: 'manage-menu',
        templateUrl: './manage-menu.component.html',
        styleUrls: ['./manage-menu.component.css']
    }),
    __metadata("design:paramtypes", [router_1.Router,
        menu_service_1.MenuService])
], ManageMenuComponent);
exports.ManageMenuComponent = ManageMenuComponent;
//# sourceMappingURL=manage-menu.component.js.map