import {Component, Inject, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {RoomService} from '../../../../features/rooms/services/room.service';
import {RoomModel} from '../../../../features/rooms/models/room.model';

@Component({
  selector: 'app-rooms-edit',
  templateUrl: './rooms-edit.component.html',
  styleUrls: ['./rooms-edit.component.sass']
})
export class RoomsEditComponent implements OnInit {
  roomForm: FormArray;
  hotelId: number;
  fb: FormBuilder;

  constructor(fb: FormBuilder, private readonly roomService: RoomService, @Inject(MAT_DIALOG_DATA) public data,
              public dialogRef: MatDialogRef<RoomsEditComponent>
  ) {
    this.fb = fb;
    this.hotelId = data.hotelId;
    this.roomForm = fb.array([this.getRoomForm()]);

  }

  getRoomForm(): FormGroup {
    return this.fb.group({
      hotelId: this.hotelId,
      beds: ['', [Validators.required, Validators.pattern('[0-9]+')]],
      dailyPrice: ['', [Validators.required, Validators.pattern('[0-9]+')]],
      description: ['', [Validators.required, Validators.minLength(30)]],
    });
  }

  ngOnInit(): void {
  }

  get roomsArray() {
    return this.roomForm;
  }

  addRoom(): void {
    this.roomsArray.push(this.getRoomForm());
  }

  deleteRoom(index: number): void {
    this.roomsArray.removeAt(index);

  }
  submit(){
    console.info(this.roomForm.value);
  this.roomService.addRooms(this.roomForm.value as RoomModel[],this.hotelId).subscribe((res)=>{
    this.dialogRef.close(true);
  },
    error => {this.dialogRef.close(false);})


  }

}
