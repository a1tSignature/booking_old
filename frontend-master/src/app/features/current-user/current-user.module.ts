import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserMenuComponent} from './user-menu/user-menu.component';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {RouterModule} from '@angular/router';
import {CoreModule} from '../../core/core.module';


@NgModule({
  declarations: [UserMenuComponent],
  exports: [UserMenuComponent],
  imports: [
    CommonModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    RouterModule,
    CoreModule
  ]
})
export class CurrentUserModule {
}
