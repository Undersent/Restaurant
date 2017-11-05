import { Injectable } from '@angular/core';
import { Http }       from '@angular/http';

import { Observable }     from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { Staff }           from '../model/staff';

@Injectable()
export class StaffSearchService {

  constructor(private http: Http) {}

  search(term: string): Observable<Staff[]> {
    return this.http
               .get(`get/staff/?name=${term}`)
               .map(response => response.json().data as Staff[]);
  }
}
