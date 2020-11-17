import { Component } from '@angular/core';
import {CurrentUserService} from './core/auth/current-user.service';
import {Role} from './core/auth/role.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent {
  title = 'miniBookingFrontend';
  Role = Role;
  constructor(private readonly userService: CurrentUserService){}
}
