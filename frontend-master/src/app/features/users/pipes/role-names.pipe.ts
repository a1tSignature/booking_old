import { Pipe, PipeTransform } from '@angular/core';
import {Role} from '../../../core/auth/role.model';

@Pipe({
  name: 'roleNames'
})
export class RoleNamesPipe implements PipeTransform {

  transform(value: Role): String[] {
    return Object.keys(value);
  }

}
