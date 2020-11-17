import {Component, OnInit} from '@angular/core';
import {CurrentUserService} from '../../../core/auth/current-user.service';
import {Role} from '../../../core/auth/role.model';
import {MatDrawer} from '@angular/material/sidenav';

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.sass']
})
export class UserMenuComponent implements OnInit {

  user$ = this.currentUserService.user$;
  Role = Role;

  constructor(readonly  currentUserService: CurrentUserService) {
  }

  ngOnInit(): void {
  }
  handleLogoutClick(): void {
    this.currentUserService.logout().subscribe(
      () => {},
      (error) => console.error(error)
    );
  }


}
