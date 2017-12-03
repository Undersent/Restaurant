import {Component, OnInit} from '@angular/core';
import { Router }            from '@angular/router';
import {MenuService} from "../../services/menu.service";
import {Meal} from "../../model/meal";
import {Http} from "@angular/http";

@Component({
    selector: 'edit-menu',
    templateUrl: './edit-menu.component.html',
    styleUrls: [ './manage-menu.component.css' ]
})
export class EditMenuComponent implements OnInit{

    menu: Meal[];

    constructor(private menuService: MenuService) { }

    ngOnInit(): void {
        this.setMeals();
    }

    private setMeals() {
        this.menuService.getAllMeal()
            .then((meals: Meal[]) => this.menu = meals);
    }

    submitMeal(name: string, price: number,
               availability: boolean, id?: number) {
        if(id == undefined || id == null){
            this.menuService.create(name, price, availability);
        }else {
            var meal: Meal = new Meal();
            meal.mealName = name;
            meal.price = price;
            meal.available =availability;
            meal.mealId = id;
            this.menuService.update(meal);
        }
        this.setMeals();
    }
}
