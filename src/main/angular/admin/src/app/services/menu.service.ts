import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import {Meal} from "../model/meal";

@Injectable()
export class MenuService {

    private updateHeaders = new Headers({'Content-Type': 'application/json'});
    private mealUrl = 'http://localhost:8080/get/meal';

    constructor(private http: Http) { }

    getAllMeal(): Promise<Meal[]> {
        let url = this.mealUrl + '/all?page=0&size=20&sort=price,desc';
        return this.http.get(url)
            .toPromise()
            .then(response => {
                return response.json() as Meal[];
            })
            .catch(this.handleError);
    }

    getMeal(id: number): Promise<Meal> {
        const url = `${this.mealUrl}/id?id=${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as Meal)
            .catch(this.handleError);
    }

    delete(id: number): Promise<void> {
        const url = `${this.mealUrl}/${id}`;
        return this.http.delete(url, {headers: this.updateHeaders})
            .toPromise()
            .then(() => null)
            .catch(this.handleError);
    }

    create(name: string, price: number, isAvailable: boolean): Promise<Meal> {
        let meal: Meal = new Meal();
        meal.name = name;
        meal.price = price;
        meal.isAvailable = isAvailable;
        return this.http
            .post(this.mealUrl, JSON.stringify(meal), {headers: this.updateHeaders})
            .toPromise()
            .then(res => res.json().data as Meal)
            .catch(this.handleError);
    }

    update(meal: Meal): Promise<Meal> {
        const url = `${this.mealUrl}/${meal.id}`;
        return this.http
            .put(url, JSON.stringify(meal), {headers: this.updateHeaders})
            .toPromise()
            .then(() => meal)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}

