import { Injectable }    from '@angular/core';

import 'rxjs/add/operator/toPromise';
import {Staff} from '../model/staff';

@Injectable()
export class RouteParamsService {

    person: Staff;
}

