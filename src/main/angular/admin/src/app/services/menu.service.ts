import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import {Meal} from "../model/meal";

@Injectable()
export class MenuService {

    private updateHeaders = new Headers({'Content-Type': 'application/json'});
    private username: string = '123';
    private password: string = 'admin';
    private mealUrl = 'http://localhost:8080/get/meal';
    private deleteMealUrl = 'http://localhost:8080/admin/add/meal/delete';
    private addMealUrl = 'http://localhost:8080/admin/add/meal/new';
    private updateMealUrl = 'http://localhost:8080/admin/add/meal';

    constructor(private http: Http) {
        this.updateHeaders.append("Authorization", "Basic "
            + btoa(this.username + ":" + this.password));
    }

    getAllMeal(): Promise<Meal[]> {
        let url = this.mealUrl + '/all?page=0&size=20&sort=price,desc';
        return this.http.get(url)
            .toPromise()
            .then(response => {
                return response.json().content as Meal[];
            })
            .catch(this.handleError);
    }

    delete(id: number): Promise<void> {
        const url = `${this.deleteMealUrl}?id=${id}`;
        return this.http.post(url, JSON.stringify(id),{headers: this.updateHeaders})
            .toPromise()
            .catch(this.handleError);
    }

    create(name: string, price: number, isAvailable: boolean): Promise<Meal> {
        let meal: Meal = new Meal();
        meal.mealName = name;
        meal.price = price;
        meal.available = isAvailable;
        return this.http
            .post(this.addMealUrl, JSON.stringify(meal), {headers: this.updateHeaders})
            .toPromise()
            .catch(this.handleError);
    }

    update(meal: Meal): Promise<Meal> {
        if (meal.price == 0 || this.isNullOrUndefined(meal.price) ||
            meal.mealName == '' || this.isNullOrUndefined(meal.mealName))
            this.delete(meal.mealId);
        else
        return this.http
            .post(this.updateMealUrl, JSON.stringify(meal), {headers: this.updateHeaders})
            .toPromise()
            .catch(this.handleError);
    }

    private isNullOrUndefined(it: any) {
        return it == null || it == undefined;
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}

