import {Component, OnInit} from '@angular/core';
import { Router }            from '@angular/router';
import {MenuService} from "../../services/menu.service";
import {Meal} from "../../model/meal";

@Component({
    selector: 'edit-menu',
    templateUrl: './edit-menu.component.html',
    styleUrls: [ './manage-menu.component.css' ]
})
export class EditMenuComponent implements OnInit{

    menu: Meal[];

    constructor(private router: Router,
                private menuService: MenuService) { }

    ngOnInit(): void {
        this.menuService.getAllMeal()
            .then((meals: Meal[]) => this.menu = meals);
    }



}
