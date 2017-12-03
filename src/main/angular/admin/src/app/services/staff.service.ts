import { Injectable }    from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Staff } from '../model/staff';


@Injectable()
export class StaffService {

    private updateHeaders = new Headers({'Content-Type': 'application/json'});
    private username: string = '123';
    private password: string = 'admin';
    private staffUrl = 'http://localhost:8080/get/staff';
    private updateStaffUrl = 'http://localhost:8080/admin/update/staff';
    private createWaiterUrl = 'http://localhost:8080/admin/add/staff/waiter';
    private createCookUrl = 'http://localhost:8080/admin/add/staff/cook';

    constructor(private http: Http) {
        this.updateHeaders.append("Authorization", "Basic "
            + btoa(this.username + ":" + this.password)); }

    getWholeStaff(): Promise<Staff[]> {
        let url = this.staffUrl + '/all';
        return this.http.get(url, {headers: this.updateHeaders})
            .toPromise()
            .then(response => {
                return response.json() as Staff[];
            })
            .catch(this.handleError);
    }

    getPerson(id: number): Promise<Staff> {
        const url = `${this.staffUrl}/id?id=${id}`;
        return this.http.get(url, {headers: this.updateHeaders})
            .toPromise()
            .then(response => response.json() as Staff)
            .catch(this.handleError);
    }

    create(person: Staff, role: string): Promise<string> {
        const url = this.getUrlFromRole(role);
        return this.http
            .post(url, JSON.stringify(person), {headers: this.updateHeaders})
            .toPromise()
            .then((response: Response) => {
                return 'OK';
            })
            .catch(this.handleErrorFromCreate);
    }

    update(person: Staff) {
        return this.http
            .post(this.updateStaffUrl, JSON.stringify(person), {headers: this.updateHeaders})
            .toPromise()
            .catch(this.handleError);
    }

    handleErrorFromCreate(error: any): any {
        if (error.json().message == 'staff with that pesel exists') {
            return new Promise((resolve, reject) => { resolve("staff with that pesel exists");});
        } else {
            return Promise.reject(error.message || error);
        }
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

    private getUrlFromRole(role: string) {
        if (role == 'Waiter'){
            return this.createWaiterUrl;
        } else {
            return this.createCookUrl;
        }
    }
}
