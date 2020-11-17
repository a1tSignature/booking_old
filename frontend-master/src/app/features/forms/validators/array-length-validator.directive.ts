import {Directive} from '@angular/core';
import {AbstractControl, ValidationErrors, Validator, ValidatorFn} from '@angular/forms';

export const arrayLength: ValidatorFn = ((control) => {
  if ((control.value as any[]).length > 3) {
    return null;
  }
  return {
    arrayLength: true
  };

});

@Directive({
  selector: '[appArrayLengthValidator]'
})
export class ArrayLengthValidatorDirective implements Validator {


  constructor() {
  }

  validate(control: AbstractControl): ValidationErrors | null {
    return arrayLength(control);
  }

}
