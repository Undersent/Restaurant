import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';
import { Observable }        from 'rxjs/Observable';
import { Subject }           from 'rxjs/Subject';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import { StaffSearchService } from '../services/staff-search.service';
import { Staff } from '../model/staff';

@Component({
  selector: 'person-search',
  templateUrl: './staff-search.component.html',
  styleUrls: [ './staff-search.component.css' ],
  providers: [StaffSearchService]
})
export class StaffSearchComponent implements OnInit {
  staff: Observable<Staff[]>;
  private searchTerms = new Subject<string>();

  constructor(
    private staffSearchService: StaffSearchService,
    private router: Router) {}

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.staff = this.searchTerms
      .debounceTime(300)
      .distinctUntilChanged()
      .switchMap(term => term
        ? this.staffSearchService.search(term)
        : Observable.of<Staff[]>([]))
      .catch(error => {
        console.log(error);
        return Observable.of<Staff[]>([]);
      });
  }

  gotoDetail(person: Staff): void {
    let link = ['/detail', person.staffId];
    this.router.navigate(link);
  }
}
