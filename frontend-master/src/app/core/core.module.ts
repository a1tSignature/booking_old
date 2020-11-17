import {NgModule, Optional, SkipSelf} from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserMenuComponent } from '../features/current-user/user-menu/user-menu.component';
import {MatButtonModule} from '@angular/material/button';
import {PermissionDirective} from './permission.directive';
import {HttpClientModule} from '@angular/common/http';
import {CURRENT_USER_INITIALIZER} from './auth/current-user.service';
import {CREDENTIALS_INTERCEPTOR} from './auth/credentials.interceptor';



@NgModule({
  declarations: [PermissionDirective],
  imports: [
    CommonModule,
    MatButtonModule
  ],
  exports: [HttpClientModule, PermissionDirective],
  providers: [CREDENTIALS_INTERCEPTOR, CURRENT_USER_INITIALIZER],
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() coreModule : CoreModule){
    if(coreModule!= undefined)
      throw  Error('Core module must be defined');

  }
}
