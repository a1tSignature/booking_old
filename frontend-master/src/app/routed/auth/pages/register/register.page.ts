import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CurrentUserService} from '../../../../core/auth/current-user.service';
import {Role} from '../../../../core/auth/role.model';
import {UserModel} from '../../../../features/users/models/user-model';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import { passwordsNotMatch} from '../../../../features/forms/validators/password-validator.directive';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.sass']
})
export class RegisterPage implements OnInit {
  userForm: FormGroup;
  role = Role;
  keys: string[];
  repeatedPassword = '';


  constructor(private readonly  fb: FormBuilder, private readonly  userService: CurrentUserService, private readonly  router: Router, private _snackBar: MatSnackBar) {
    this.userForm = fb.group({
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword:['',Validators.required],
      role: ['', Validators.required],
      personalInfo: fb.group({
        firstName: ['', [Validators.required, Validators.maxLength(50)]],
        lastName: ['', [Validators.required, Validators.maxLength(50)]],
        email: ['', [Validators.required, Validators.email]],
        phoneNumber: ['', [Validators.required, Validators.pattern('[0-9 ]{11}')]],
        location: fb.group({
          country: ['', [Validators.required,Validators.maxLength(50)]],
          city: ['', [Validators.required,Validators.maxLength(50)]],
          street: ['',[Validators.required,Validators.maxLength(50)]],
          house: ['', [Validators.required,Validators.maxLength(50)]],
        })
      })
    },{validators:passwordsNotMatch,updateOn: 'change'});
  }

  onRegisterClick(): void {
    console.info(this.userForm.value as UserModel);
    this.userService.register(this.userForm.value as UserModel).subscribe(() => {
        this.router.navigate(['auth/login']);
      },
      (error) => {
        this._snackBar.open('Ошибка регистрации, логин, email и телефон должны быть уникальны', 'закрыть', {duration: 4000});
      });
  }

  ngOnInit(): void {
  }




}
