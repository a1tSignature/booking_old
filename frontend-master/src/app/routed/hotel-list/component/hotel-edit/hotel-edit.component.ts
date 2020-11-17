import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {HotelDetailModel} from '../../../../features/hotels/models/hotel.model';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {arrayLength} from '../../../../features/forms/validators/array-length-validator.directive';
import {HotelListService} from '../../../../features/hotels/services/hotel-list.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FileService} from '../../../../features/api/services/file.service';
import {MatChipInputEvent} from '@angular/material/chips';
import {CreatedHotelModel} from '../../../../features/hotels/models/created-hotel.model';
import {LocationModel} from '../../../../features/hotels/models/location.model';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-hotel-edit',
  templateUrl: './hotel-edit.component.html',
  styleUrls: ['./hotel-edit.component.sass']
})
export class HotelEditComponent implements OnInit {

  hotelForm: FormGroup;
  amenities: string[] = [];
  selectable = true;
  removable = true;
  addOnBlur = true;
  hotelId: number;
  hotel: Observable<HotelDetailModel>;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  arrayValidator = Validators.compose([arrayLength]);

  constructor(fb: FormBuilder, private readonly hotelListService: HotelListService, @Inject(MAT_DIALOG_DATA) public data,
              public dialogRef: MatDialogRef<HotelEditComponent>
  ) {
    this.hotelId = data.hotelId;
    this.hotel = hotelListService.getHotelDetails(this.hotelId.toString());
    this.hotel.subscribe(
      hotel => {
        this.amenities = hotel.amenities;
        this.hotelForm = this.getHotelForm(fb, hotel);

      }
    );
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.amenities.push(value.trim());
    }

    if (input) {
      input.value = '';
    }
    this.notifyValidator('amenities');
  }

  remove(amenity: string): void {
    const index = this.amenities.indexOf(amenity);

    if (index >= 0) {
      this.amenities.splice(index, 1);
    }
    this.notifyValidator('amenities');
  }

  ngOnInit(): void {
  }

  send(): void {
    this.hotelListService.updateHotel(this.hotelForm.value as CreatedHotelModel, this.hotelId).subscribe((res) => {
        this.dialogRef.close(true);
      },
      error => {
        this.dialogRef.close(false);
      });
  }

  close(): void {
    this.dialogRef.close();
  }

  getHotelForm(fb: FormBuilder, hotel: HotelDetailModel = undefined): FormGroup {
    return fb.group({
      name: [hotel.name, Validators.required],
      description: [hotel.description, [Validators.required, Validators.minLength(50),Validators.maxLength(450)]],
      location: fb.group({
        country: [hotel.location.country, Validators.required],
        city: [hotel.location.city, Validators.required],
        street: [hotel.location.street, Validators.required],
        house: [hotel.location.house, Validators.required],
      }),
      amenities: [this.amenities, [Validators.required, this.arrayValidator]],
    });
  }

  notifyValidator(controlName: string) {
    this.hotelForm.controls[controlName].updateValueAndValidity();
  }


}
