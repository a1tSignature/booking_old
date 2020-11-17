import {Directive} from '@angular/core';
import { FormGroup, ValidationErrors, ValidatorFn} from '@angular/forms';

export const passwordsNotMatch: ValidatorFn = ((group) => {
  let pass = group.get('password').value;
  let confirmPassword = group.get('confirmPassword').value;
  if (pass == confirmPassword) {
    return null;
  }
  return {
    passwordsNotMatch: true
  };

});

@Directive({
  selector: '[appPasswordValidator]'
})
export class PasswordValidatorDirective {
  constructor() {
  }

  validate(group: FormGroup): ValidationErrors | null {
    return passwordsNotMatch(group);
  }

}




