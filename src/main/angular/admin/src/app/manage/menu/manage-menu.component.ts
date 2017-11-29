import {Component, OnInit} from '@angular/core';
import { Router }            from '@angular/router';
import {MenuService} from "../../services/menu.service";
import {Meal} from "../../model/meal";

@Component({
    selector: 'manage-menu',
    templateUrl: './manage-menu.component.html',
    styleUrls: [ './manage-menu.component.css' ]
})
export class ManageMenuComponent implements OnInit{

    menu: Meal[];

    constructor(private router: Router,
                private menuService: MenuService) { }

    ngOnInit(): void {
        this.menuService.getAllMeal()
            .then((meals: Meal[]) => this.menu = meals);
    }



}
