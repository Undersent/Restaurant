import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Staff } from '../model/staff';

@Injectable()
export class StaffService {

  private updateHeaders = new Headers({'Content-Type': 'application/json'});
  private staffUrl = 'http://localhost:8080/get/staff';

  constructor(private http: Http) { }

  getWholeStaff(): Promise<Staff[]> {
      let url = this.staffUrl + '/all';
      return this.http.get(url)
               .toPromise()
               .then(response => {
                   return response.json() as Staff[];
               })
               .catch(this.handleError);
  }


  getPerson(id: number): Promise<Staff> {
    const url = `${this.staffUrl}/id?id=${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Staff)
      .catch(this.handleError);
  }

  delete(id: number): Promise<void> {
    const url = `${this.staffUrl}/${id}`;
    return this.http.delete(url, {headers: this.updateHeaders})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  create(name: string, surname: string, role: string, pesel?: string): Promise<Staff> {
    let person: Staff = new Staff();
    person.firstName = name;
    person.lastName = surname;
    person.pesel = pesel;
    person.roles = new Set(role);
    return this.http
      .post(this.staffUrl, JSON.stringify(person), {headers: this.updateHeaders})
      .toPromise()
      .then(res => res.json().data as Staff)
      .catch(this.handleError);
  }

  update(person: Staff): Promise<Staff> {
    const url = `${this.staffUrl}/${person.staffId}`;
    return this.http
      .put(url, JSON.stringify(person), {headers: this.updateHeaders})
      .toPromise()
      .then(() => person)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}

