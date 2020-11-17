import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatChipInputEvent} from '@angular/material/chips';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {arrayLength} from '../../../../features/forms/validators/array-length-validator.directive';
import {HotelListService} from '../../../../features/hotels/services/hotel-list.service';
import {CreatedHotelModel} from '../../../../features/hotels/models/created-hotel.model';
import {ActivatedRoute, Router} from '@angular/router';
import {FileService} from '../../../../features/api/services/file.service';
import {RoomService} from '../../../../features/rooms/services/room.service';
import {error} from 'selenium-webdriver';
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-hotel-add',
  templateUrl: './hotel-add.page.html',
  styleUrls: ['./hotel-add.page.sass']
})
export class HotelAddPage implements OnInit {
  hotelForm: FormGroup;
  amenities: string[] = [];
  files: File[] = [];
  selectable = true;
  removable = true;
  addOnBlur = true;
  hotelId: number;
  fb: FormBuilder;

  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  arrayValidator = Validators.compose([arrayLength]);

  constructor(fb: FormBuilder, private readonly hotelListService: HotelListService, private router: Router, private readonly fileService: FileService,
              private readonly roomService: RoomService, private _snackBar: MatSnackBar
  ) {
    this.fb = fb;
    this.hotelForm = this.getHotelForm();
  }


  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;
    if(value.length>=50)
    {
      this._snackBar.open('Too long amenity name,', 'close', {duration: 4000})
      return;
    }
    if ((value || '').trim()) {
      this.amenities.push(value.trim());
    }

    if (input) {
      input.value = '';
    }
    this.notifyValidator('amenities');
    console.info(this.roomsArray.value);
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
    this.hotelListService.addHotel(this.hotelForm.value as CreatedHotelModel).subscribe((res) => {
        let hotelId = res.id;
        this.roomService.addRooms(this.roomsArray.value, hotelId).subscribe((res) => {
            this.fileService.addFiles(this.files, hotelId).subscribe((res) => {
              this.router.navigate([`/hotels/${hotelId}`]);
            }, (error) => {
              this._snackBar.open('Error adding images, try pictures with less size', 'close', {duration: 4000}).afterDismissed(
              ).subscribe(() => {
                  this.router.navigate([`/hotels/${hotelId}`]);
                }
              );

            });
          }, (error) => {
            this._snackBar.open('Error adding hotel rooms,', 'close', {duration: 4000}).afterDismissed().subscribe(() => {
                this.router.navigate([`/hotels/${hotelId}`]);
              }
            );
          }
        );

      },
      (error) => {
        this._snackBar.open('Error creating hotel,', 'close', {duration: 4000});

      }
    );
  }

  onSelect(event) {
    console.log(event);
    this.files.push(...event.addedFiles);
    this.notifyValidator('images');
  }

  onRemove(event) {
    console.log(event);
    this.files.splice(this.files.indexOf(event), 1);
    this.notifyValidator('images');

  }

  getHotelForm(): FormGroup {
    return this.fb.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      description: ['', [Validators.required, Validators.minLength(50),Validators.maxLength(450)]],
      location: this.fb.group({
        country: ['', [Validators.required, Validators.maxLength(50)]],
        city: ['', [Validators.required, Validators.maxLength(50)]],
        street: ['', [Validators.required, Validators.maxLength(50)]],
        house: ['', [Validators.required, Validators.maxLength(50)]],
      }),
      rooms: this.fb.array([this.getRoomControl()], {validators: this.arrayValidator}),
      images: [this.files, [Validators.required, this.arrayValidator]],
      amenities: [this.amenities, [Validators.required, this.arrayValidator]],
    });
  }

  notifyValidator(controlName: string) {
    this.hotelForm.controls[controlName].updateValueAndValidity();
  }

  get roomsArray() {
    return this.hotelForm.get('rooms') as FormArray;
  }

  addRoom(): void {
    this.roomsArray.push(this.getRoomControl());
    this.notifyValidator('rooms');
    console.info('controls: ', this.roomsArray.controls);
    console.info('hote', this.hotelForm);
  }

  getRoomControl(): FormGroup {
    return this.fb.group({
      beds: ['', [Validators.required, Validators.pattern('[0-9]+')]],
      dailyPrice: ['', [Validators.required, Validators.pattern('[0-9]+')]],
      description: ['', [Validators.required, Validators.minLength(30)]],
    });
  }

  deleteRoom(index: number): void {
    this.roomsArray.removeAt(index);
    this.notifyValidator('rooms');

  }

}
