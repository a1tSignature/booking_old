import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RoleNamesPipe} from './pipes/role-names.pipe';


@NgModule({
  declarations: [RoleNamesPipe],
  imports: [
    CommonModule
  ],
  exports: [RoleNamesPipe]
})
export class UsersModule {
}
